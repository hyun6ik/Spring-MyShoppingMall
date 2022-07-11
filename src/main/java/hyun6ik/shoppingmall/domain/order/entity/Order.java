package hyun6ik.shoppingmall.domain.order.entity;

import hyun6ik.shoppingmall.domain.base.BaseTimeEntity;
import hyun6ik.shoppingmall.domain.order.constant.OrderStatus;
import hyun6ik.shoppingmall.global.utils.TokenGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    private static final String PREFIX_ORDER = "order_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    private Long memberId;

    private String orderToken;

    @Builder
    public Order(OrderStatus orderStatus, LocalDateTime orderTime, Long memberId) {
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
        this.memberId = memberId;
        this.orderToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ORDER);
    }
}
