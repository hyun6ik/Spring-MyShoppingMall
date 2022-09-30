package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import hyun6ik.shoppingmall.domain.order.entity.OrderItems;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OrderFactory {

    public OrderItems createOrderItems(OrderItem... orderItems) {
        List<OrderItem> orderItemList = new ArrayList<>(Arrays.asList(orderItems));
        return new OrderItems(orderItemList);
    }
}
