package hyun6ik.shoppingmall.application.order;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.service.ItemService;
import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import hyun6ik.shoppingmall.domain.order.service.OrderService;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ItemService itemService;


    @Transactional
    public OrderDto.Response createOrder(OrderDto.Request request, Long memberId) {
        final Item item = itemService.getItemBy(request.getItemId());
        final OrderDto.Response orderResponse = orderService.createOrder(item, memberId, request.getCount());
        item.decreaseStock(request.getCount());
        return orderResponse;
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        final OrderItem cancelOrderItem = orderService.cancelOrder(orderId);
        itemService.increaseStock(cancelOrderItem);
    }
}
