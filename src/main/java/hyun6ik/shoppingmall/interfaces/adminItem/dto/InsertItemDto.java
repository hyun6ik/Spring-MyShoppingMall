package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InsertItemDto {

    @Getter
    @Setter
    public static class Request {

        @NotBlank
        private String itemName;

        @NotNull
        private Integer price;

        @NotBlank
        private String itemDetail;

        @NotNull
        private Integer stockNumber;

        @NotNull
        private ItemSellStatus itemSellStatus;

        @NotNull
        private Long deliveryId;

        private List<MultipartFile> itemImageFiles;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Response {

        private final Long itemId;
        private final String itemName;
        private final int price;
        private final int stockNumber;
        private final String itemDetail;
        private final ItemSellStatus itemSellStatus;
        private final Long memberId;
        private final Long deliveryId;
        private final List<ItemImageDto> itemImageDtos;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class ItemImageDto {
        private final String imageName;
        private final String imageUrl;
        private final String originalImageName;
        private final boolean isRepImage;
    }
}
