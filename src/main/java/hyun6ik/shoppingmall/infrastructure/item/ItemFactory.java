package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.infrastructure.file.S3Service;
import hyun6ik.shoppingmall.infrastructure.file.UploadFile;
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

    public List<ItemImage> createItemImages(Item item, List<MultipartFile> imageFiles) {

        List<ItemImage> itemImages = new ArrayList<>();

        for (int i = 0; i < IMAGE_MAX_SIZE; i++) {
            final UploadFile uploadFile = s3Service.uploadImage(imageFiles.get(i));
            if (RepImage(i)) {
                itemImages.add(ItemImage.builder()
                        .item(item)
                        .imageName(uploadFile.getStoreFileName())
                        .imageUrl(uploadFile.getFileUploadUrl())
                        .originalImageName(uploadFile.getOriginalFileName())
                        .isRepImage(true)
                        .build());
                continue;
            }
            if (notUploadImage(imageFiles, i)) {
                itemImages.add(ItemImage.builder()
                        .item(item)
                        .build());
                continue;
            }
            itemImages.add(ItemImage.builder()
                    .item(item)
                    .imageName(uploadFile.getStoreFileName())
                    .imageUrl(uploadFile.getFileUploadUrl())
                    .originalImageName(uploadFile.getOriginalFileName())
                    .isRepImage(false)
                    .build());
        }

        return itemImages;
    }

    private boolean RepImage(int i) {
        return i == 0;
    }


    private boolean notUploadImage(List<MultipartFile> imageFiles, int i) {
        return StringUtils.isBlank(imageFiles.get(i).getOriginalFilename());
    }
}
