package hyun6ik.shoppingmall.domain.order.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.order.entity.OrderItems;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDto.Response createOrder(Item item, Long memberId, Integer count);

    Page<OrderHistDto> getOrderHistDtosBy(Long memberId, Pageable pageable);

    OrderItems cancelOrder(Long orderId);
}
