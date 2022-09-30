package hyun6ik.shoppingmall.common.factory;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemDocument;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.domain.item.entity.ItemImages;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemImageDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemFactory {

    public static ItemRequestDto.Insert getItemInsertRequestDto() throws IOException {
        final List<MultipartFile> multipartFiles = createMultipartFiles(5);
        return ItemRequestDto.Insert
                .builder()
                .itemName("상품1")
                .itemDetail("상세설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .deliveryId(1L)
                .price(10000)
                .stockNumber(10)
                .itemImageFiles(multipartFiles)
                .build();
    }

    public static ItemRequestDto.Update getItemUpdateRequestDto() throws IOException {
        final List<MultipartFile> multipartFiles = createMultipartFiles(5);
        final List<String> originalNames = createOriginalNames(5);
        final List<ItemImageDto> itemImageDtos = createItemImageDtos(5);

        final ItemRequestDto.Update request = ItemRequestDto.Update.builder()
                .itemId(1L)
                .itemName("수정")
                .itemDetail("수정상세")
                .price(20000)
                .stockNumber(20)
                .itemSellStatus(ItemSellStatus.SELL)
                .deliveryId(2L)
                .build();

        request.addMultipartFile(multipartFiles);
        request.addOriginalImageNames(originalNames);
        request.addItemImageDtos(itemImageDtos);
        return request;
    }

    public static Item item() {
        return Item.builder()
                .memberId(1L)
                .itemName("상품1")
                .itemDetail("상세설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .deliveryId(1L)
                .price(10000)
                .stockNumber(10)
                .itemImages(itemImages(5))
                .build();
    }


    public static ItemImages itemImages(int count) {
        List<ItemImage> itemImages = new ArrayList<>();
        itemImages.add(ItemImage.builder()
                .originalImageName("원래이름")
                .imageUrl("D://aa")
                .isRepImage(true)
                .imageName("이미지")
                .build());
        for (int i = 1; i < count; i++) {
            itemImages.add(ItemImage.builder()
                    .originalImageName("원래이름")
                    .imageUrl("D://aa")
                    .isRepImage(false)
                    .imageName("이미지")
                    .build());
        }
        return new ItemImages(itemImages);
    }

    public static ItemDocument itemDocument(Item item) {
        return ItemDocument.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemDetail(item.getItemDetail())
                .build();
    }

    private static List<ItemImageDto> createItemImageDtos(int count) {
        List<ItemImageDto> itemImageDtos = new ArrayList<>();
        itemImageDtos.add(ItemImageDto.builder()
                .itemImageId(1L)
                .imageName("사진이름")
                .imageUrl("fdfd/ddfd/ddd")
                .originalImageName("원래이름")
                .isRepImage(true)
                .build());
        for (int i = 2; i <= count; i++) {
            itemImageDtos.add(ItemImageDto.builder()
                    .itemImageId(Long.valueOf(i))
                    .imageName("사진이름")
                    .imageUrl("fdfd/ddfd/ddd")
                    .originalImageName("원래이름")
                    .isRepImage(false)
                    .build());
        }
        return itemImageDtos;
    }

    private static List<String> createOriginalNames(int count) {
        List<String> originalNames = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            originalNames.add("오리지널 사진 이름");
        }
        return originalNames;
    }

    private static List<MultipartFile> createMultipartFiles(int count) throws IOException {
        List<MultipartFile> multipartFiles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            multipartFiles.add(
                    new MockMultipartFile(
                            "image",
                            "test.png",
                            "image/png",
                            "test".getBytes())

                    );

        }
        return multipartFiles;
    }
}
