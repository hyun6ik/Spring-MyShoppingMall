package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.NotFoundException;
import hyun6ik.shoppingmall.infrastructure.order.repository.OrderQueryRepository;
import hyun6ik.shoppingmall.infrastructure.order.repository.OrderRepository;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderReader {

    private final OrderQueryRepository orderQueryRepository;
    private final OrderRepository orderRepository;

    public Page<OrderHistDto> getOrderHistDtosBy(Long memberId, Pageable pageable) {
        final Page<OrderHistDto> orderHistDtos = orderQueryRepository.findOrderHistDtosBy(memberId, pageable);
        orderHistDtos.iterator().forEachRemaining(orderHistDto -> orderHistDto.addOrderItemHistDtos(orderQueryRepository.findOrderHistItemDtosBy(orderHistDto.getOrderId(), orderHistDto.getItemId())));
        return orderHistDtos;
    }

    public Order getOrderBy(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ORDER));
    }
}
