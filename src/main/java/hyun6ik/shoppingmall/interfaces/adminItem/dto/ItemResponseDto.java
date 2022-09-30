package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponseDto {

    private Long itemId;
    private String itemName;
    private int price;
    private int stockNumber;
    private String itemDetail;
    private ItemSellStatus itemSellStatus;
    private Long memberId;
    private Long deliveryId;
    private List<ItemImageDto> itemImageDtos;

    @Builder
    public ItemResponseDto(Long itemId, String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus, Long memberId, Long deliveryId, List<ItemImageDto> itemImageDtos) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
        this.memberId = memberId;
        this.deliveryId = deliveryId;
        this.itemImageDtos = itemImageDtos;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ItemImageDto {
        private String imageName;
        private String imageUrl;
        private String originalImageName;
        private Boolean isRepImage;

        @Builder
        public ItemImageDto(String imageName, String imageUrl, String originalImageName, Boolean isRepImage) {
            this.imageName = imageName;
            this.imageUrl = imageUrl;
            this.originalImageName = originalImageName;
            this.isRepImage = isRepImage;
        }
    }
}
