package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.infrastructure.order.repository.OrderQueryRepository;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderReader {

    private final OrderQueryRepository orderQueryRepository;


    public Page<OrderHistDto> getOrderHistDtosBy(Long memberId, Pageable pageable) {
        final Page<OrderHistDto> orderHistDtos = orderQueryRepository.findOrderHistDtosBy(memberId, pageable);
        orderHistDtos.iterator().forEachRemaining(orderHistDto -> orderHistDto.addOrderItemHistDtos(orderQueryRepository.findOrderHistItemDtosBy(orderHistDto.getOrderId(), orderHistDto.getItemId())));
        return orderHistDtos;
    }
}
