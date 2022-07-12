package hyun6ik.shoppingmall.domain.order.entity;

import hyun6ik.shoppingmall.domain.base.BaseTimeEntity;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private Integer count;

    @Column(nullable = false, length = 11)
    private Integer orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long itemId;

    @Builder
    public OrderItem(Integer count, Integer orderPrice, Order order, Long itemId) {
        this.count = count;
        this.orderPrice = orderPrice;
        this.order = order;
        this.itemId = itemId;
    }

    public static OrderItem createOrderItem(Order order, Item item, int count) {
        return OrderItem.builder()
                .count(count)
                .orderPrice(item.getPrice() * count)
                .itemId(item.getId())
                .order(order)
                .build();
    }
}
