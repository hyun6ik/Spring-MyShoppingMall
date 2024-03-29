package hyun6ik.shoppingmall.interfaces.item.dto;

import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.order.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OrderDto {

    @Getter
    @Setter
    public static class Request {

        @NotNull
        private Long itemId;
        @NotNull
        private Integer count;
    }

    @Getter
    @Setter
    public static class Response {
        private OrderStatus orderStatus;
        private LocalDateTime orderTime;
        private Long memberId;
        private String orderToken;
        private OrderItemDto orderItemDto;

        @Getter
        @Setter
        public static class OrderItemDto {
            private Integer count;
            private Integer orderPrice;
            private Long itemId;
            private ItemSellStatus itemSellStatus;
        }
    }

}
