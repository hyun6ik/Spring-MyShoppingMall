package hyun6ik.shoppingmall.domain.order.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void belongTo(Order order) {
        orderItems.forEach(orderItem -> orderItem.belongTo(order));
    }
}
