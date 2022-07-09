package hyun6ik.shoppingmall.domain.delivery.entity;

import hyun6ik.shoppingmall.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer deliveryFee;

    @Column(length = 100)
    private String deliveryName;

    private Long memberId;

    public Delivery(Integer deliveryFee, String deliveryName, Long memberId) {
        this.deliveryFee = deliveryFee;
        this.deliveryName = deliveryName;
        this.memberId = memberId;
    }
}
