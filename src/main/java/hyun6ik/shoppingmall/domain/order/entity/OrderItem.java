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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;

    @Builder
    public OrderItem(Integer count, Integer orderPrice, Item item) {
        this.count = count;
        this.orderPrice = orderPrice;
        this.item = item;
    }

    public static OrderItem createOrderItem(Item item, int count) {
        return OrderItem.builder()
                .count(count)
                .orderPrice(item.getPrice() * count)
                .item(item)
                .build();
    }

    public void belongTo(Order order) {
        this.order = order;
    }
}
