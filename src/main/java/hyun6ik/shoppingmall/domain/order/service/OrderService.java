package hyun6ik.shoppingmall.domain.order.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;

public interface OrderService {
    OrderDto.Response createOrder(Item item, Long memberId, Integer count);
}
