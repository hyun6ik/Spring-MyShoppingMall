package hyun6ik.shoppingmall.domain.order.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import hyun6ik.shoppingmall.domain.order.entity.OrderItems;
import hyun6ik.shoppingmall.global.annotation.LogTrace;
import hyun6ik.shoppingmall.infrastructure.order.*;
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
    private final OrderFactory orderFactory;

    @Override
    @Transactional
    public OrderDto.Response createOrder(Item item, Long memberId, Integer count) {
        orderValidator.validateStock(item, count);

        final OrderItem initOrderItem = OrderItem.createOrderItem(item, count);
        final OrderItems initOrderItems = orderFactory.createOrderItems(initOrderItem);

        final Order initOrder = Order.createOrder(memberId, LocalDateTime.now(), initOrderItems);
        final Order order = orderStore.store(initOrder);

        return orderDtoMapper.of(order);
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
