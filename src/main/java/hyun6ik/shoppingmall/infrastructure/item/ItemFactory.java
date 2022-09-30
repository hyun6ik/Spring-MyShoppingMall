package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.domain.item.entity.ItemImages;
import hyun6ik.shoppingmall.infrastructure.file.S3Service;
import hyun6ik.shoppingmall.infrastructure.file.UploadFile;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemFactory {

    private static final int IMAGE_MAX_SIZE = 5;

    private final S3Service s3Service;

    public ItemImages createItemImages(List<MultipartFile> imageFiles) {

        List<ItemImage> itemImages = new ArrayList<>();

        for (int i = 0; i < IMAGE_MAX_SIZE; i++) {
            final UploadFile uploadFile = s3Service.uploadImage(imageFiles.get(i));
            if (RepImage(i)) {
                itemImages.add(ItemImage.builder()
                        .imageName(uploadFile.getStoreFileName())
                        .imageUrl(uploadFile.getFileUploadUrl())
                        .originalImageName(uploadFile.getOriginalFileName())
                        .isRepImage(true)
                        .build());
                continue;
            }
            if (notUploadImage(imageFiles, i)) {
                itemImages.add(ItemImage.builder()
                        .build());
                continue;
            }
            itemImages.add(ItemImage.builder()
                    .imageName(uploadFile.getStoreFileName())
                    .imageUrl(uploadFile.getFileUploadUrl())
                    .originalImageName(uploadFile.getOriginalFileName())
                    .isRepImage(false)
                    .build());
        }

        return new ItemImages(itemImages);
    }

    private boolean RepImage(int i) {
        return i == 0;
    }


    private boolean notUploadImage(List<MultipartFile> imageFiles, int i) {
        return StringUtils.isBlank(imageFiles.get(i).getOriginalFilename());
    }

    public ItemImages updateItemImages(ItemRequestDto.Update request, ItemImages itemImages) {
        List<ItemImage> imageList = new ArrayList<>();

        for (int i = 0; i < request.getItemImageFiles().size(); i++) {
            final ItemImage updateItemImage = updateItemImage(itemImages.getItemImages().get(i), request.getItemImageFiles().get(i), request.getOriginalImageNames().get(i));
            imageList.add(updateItemImage);
        }

        return new ItemImages(imageList);
    }

    public ItemImage updateItemImage(ItemImage itemImage, MultipartFile imageFile, String existImage){
        if (unModifiedImage(itemImage, imageFile, existImage)) {
            return itemImage;
        }

        if (alreadyNullImage(itemImage, imageFile, existImage)) {
            return itemImage;
        }

        if (deletedImage(itemImage, imageFile)) {
            s3Service.deleteImage(itemImage.getImageName());
            return ItemImage.builder().isRepImage(false).build();
        }

        if (modifiedImage(itemImage)) {
            s3Service.deleteImage(itemImage.getImageName());
        }

        final UploadFile uploadFile = s3Service.uploadImage(imageFile);
        return ItemImage.of(uploadFile, itemImage.getIsRepImage());
    }

    private boolean alreadyNullImage(ItemImage itemImage, MultipartFile imageFile, String existImage) {
        return itemImage.getImageUrl() == null && imageFile.isEmpty() && existImage.isBlank();
    }

    private boolean deletedImage(ItemImage itemImage, MultipartFile imageFile) {
        return imageFile.isEmpty() && !itemImage.getImageUrl().isBlank();
    }

    private boolean modifiedImage(ItemImage itemImage) {
        return !StringUtils.isEmpty(itemImage.getImageName());
    }

    private boolean unModifiedImage(ItemImage itemImage, MultipartFile imageFile, String existImage) {
        return imageFile.isEmpty() && itemImage.getImageUrl() != null && !existImage.isBlank();
    }
}
