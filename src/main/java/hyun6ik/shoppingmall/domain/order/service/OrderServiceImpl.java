package hyun6ik.shoppingmall.domain.order.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import hyun6ik.shoppingmall.global.annotation.LogTrace;
import hyun6ik.shoppingmall.infrastructure.order.OrderDtoMapper;
import hyun6ik.shoppingmall.infrastructure.order.OrderReader;
import hyun6ik.shoppingmall.infrastructure.order.OrderStore;
import hyun6ik.shoppingmall.infrastructure.order.OrderValidator;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final OrderValidator orderValidator;
    private final OrderDtoMapper orderDtoMapper;

    @Override
    @Transactional
    public OrderDto.Response createOrder(Item item, Long memberId, Integer count) {
        orderValidator.validateStock(item, count);

        final Order initOrder = Order.createOrder(memberId, LocalDateTime.now());
        final Order order = orderStore.store(initOrder);

        final OrderItem initOrderItem = OrderItem.createOrderItem(order, item, count);
        final OrderItem orderItem = orderStore.store(initOrderItem);

        return orderDtoMapper.of(order, orderItem);
    }

    @Override
    @LogTrace
    public Page<OrderHistDto> getOrderHistDtosBy(Long memberId, Pageable pageable) {
        return orderReader.getOrderHistDtosBy(memberId, pageable);
    }

    @Override
    @Transactional
    public OrderItem cancelOrder(Long orderId) {
        final Order order = orderReader.getOrderBy(orderId);
        order.cancelOrder();
        return orderReader.getOrderItemBy(orderId);
    }
}
