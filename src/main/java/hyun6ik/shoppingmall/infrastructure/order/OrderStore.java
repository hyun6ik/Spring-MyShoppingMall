package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.infrastructure.order.repository.OrderItemRepository;
import hyun6ik.shoppingmall.infrastructure.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStore {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order store(Order initOrder) {
        orderItemRepository.save(initOrder.getOrderItem());
        return orderRepository.save(initOrder);
    }
}
