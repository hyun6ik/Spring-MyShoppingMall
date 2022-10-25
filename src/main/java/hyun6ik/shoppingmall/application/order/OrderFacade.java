package hyun6ik.shoppingmall.application.order;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.service.ItemService;
import hyun6ik.shoppingmall.domain.order.entity.Order;
import hyun6ik.shoppingmall.domain.order.service.OrderService;
import hyun6ik.shoppingmall.infrastructure.order.OrderDtoMapper;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderCancelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderDtoMapper orderDtoMapper;


    @Transactional
    @CacheEvict(
            key = "#request.itemId",
            value = "itemDtl"
    )
    public OrderDto.Response createOrder(OrderDto.Request request, Long memberId) {
        final Item item = itemService.getItemBy(request.getItemId());
        final OrderDto.Response orderResponse = orderService.createOrder(item, memberId, request.getCount());
        item.decreaseStock(request.getCount());
        return orderResponse;
    }

    @Transactional
    @CacheEvict(
            key = "#result.itemId",
            value = "itemDtl"
    )
    public OrderCancelDto cancelOrder(Long orderId) {
        final Order cancelOrder = orderService.cancelOrder(orderId);
        itemService.increaseStock(cancelOrder.getOrderItem());
        return orderDtoMapper.cancel(cancelOrder);
    }
}
