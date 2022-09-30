package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    @Mappings({@Mapping(source = "order.id", target = "orderId"), @Mapping(source = "order.orderItems.orderItems", target = "orderItemDtos")})
    OrderDto.Response of(Order order);
}
