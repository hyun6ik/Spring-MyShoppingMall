package hyun6ik.shoppingmall.common.factory;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
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

    private static List<MultipartFile> createMultipartFiles(int count) throws IOException {
        List<MultipartFile> multipartFiles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            multipartFiles.add(
                    new MockMultipartFile(
                            "image",
                            "test.png",
                            "image/png",
                            new FileInputStream("D:\\Spring\\shoppingmall\\uploadImage\\6a3bcdbf-4595-4ca2-92f9-5225feb0dc1a.PNG")

                    ));

        }
        return multipartFiles;
    }
}
