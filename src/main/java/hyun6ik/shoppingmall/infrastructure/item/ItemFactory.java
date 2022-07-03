package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.infrastructure.file.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemFactory {

    private static final int IMAGE_MAX_SIZE = 5;
    private final FileService fileService;

    public List<ItemImage> createItemImages(Item item, List<MultipartFile> imageFiles) throws IOException {

        List<ItemImage> itemImages = new ArrayList<>();

        for (int i = 0; i < IMAGE_MAX_SIZE; i++) {
            final var uploadFile = fileService.storeFile(imageFiles.get(i));
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
