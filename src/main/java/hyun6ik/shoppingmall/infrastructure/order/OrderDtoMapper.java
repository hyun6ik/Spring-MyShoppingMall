package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderCancelDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    @Mappings({@Mapping(source = "order.id", target = "orderId"), @Mapping(source = "order.orderItems.orderItems", target = "orderItemDtos")})
    OrderDto.Response of(Order order);

    @Mappings({@Mapping(source = "orderItem.item.id", target = "itemId"), @Mapping(source = "orderItem.item.itemSellStatus", target = "itemSellStatus")})
    OrderDto.Response.OrderItemDto of(OrderItem orderItem);

    @Mappings({@Mapping(source = "orderItem.item.id", target = "itemId"),
            @Mapping(source = "orderItem.order.id", target = "orderId"),
            @Mapping(source = "orderItem.item.itemSellStatus", target = "itemSellStatus")})
    OrderCancelDto cancel(OrderItem orderItem);
}
