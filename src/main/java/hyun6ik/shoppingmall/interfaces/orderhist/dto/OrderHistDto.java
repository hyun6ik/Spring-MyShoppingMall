package hyun6ik.shoppingmall.interfaces.orderhist.dto;

import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.shoppingmall.domain.order.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {

    private Long orderId;
    private Long itemId;
    private LocalDateTime orderTime;
    private OrderStatus orderStatus;
    private Integer totalPrice;
    private Integer totalDeliveryFee;

    private List<OrderItemHistDto> orderItemHistDtos;

    @QueryProjection
    public OrderHistDto(Long orderId, Long itemId, LocalDateTime orderTime, OrderStatus orderStatus, Integer totalPrice, Integer totalDeliveryFee) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.totalDeliveryFee = totalDeliveryFee;
    }

    public void addOrderItemHistDtos(List<OrderItemHistDto> orderItemHistDtos) {
        this.orderItemHistDtos = orderItemHistDtos;
    }

    @Getter @Setter
    public static class OrderItemHistDto {
        private String itemName;
        private Integer count;
        private Integer orderPrice;
        private String imageUrl;

        @QueryProjection
        public OrderItemHistDto(String itemName, Integer count, Integer orderPrice, String imageUrl) {
            this.itemName = itemName;
            this.count = count;
            this.orderPrice = orderPrice;
            this.imageUrl = imageUrl;
        }
    }
}