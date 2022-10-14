package hyun6ik.shoppingmall.interfaces.orderhist.dto;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCancelDto {

    private final Long orderId;
    private final Long itemId;
    private final ItemSellStatus itemSellStatus;

    @Builder
    public OrderCancelDto(Long orderId, Long itemId, ItemSellStatus itemSellStatus) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemSellStatus = itemSellStatus;
    }
}
