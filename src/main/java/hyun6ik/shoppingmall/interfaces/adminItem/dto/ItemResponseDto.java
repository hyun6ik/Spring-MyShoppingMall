package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    @Getter
    @Setter
    public static class ItemImageDto {
        private String imageName;
        private String imageUrl;
        private String originalImageName;
        private Boolean isRepImage;
    }
}
