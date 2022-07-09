package hyun6ik.shoppingmall.interfaces.item.dto;

import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemDtlDto {

    private Long itemId;

    private String itemName;

    private Integer price;

    private String itemDetail;

    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private Integer deliveryFee;

    private List<ItemImageDto> itemImageDtos;

    @QueryProjection
    public ItemDtlDto(Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, Integer deliveryFee) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.deliveryFee = deliveryFee;
    }

    public void addItemImageDtos(List<ItemImageDto> itemImageDtos) {
        this.itemImageDtos = itemImageDtos;
    }


    @Getter @Setter
    @ToString
    public static class ItemImageDto {
        private String imageUrl;

        @QueryProjection
        public ItemImageDto(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}