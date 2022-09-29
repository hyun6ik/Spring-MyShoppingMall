package hyun6ik.shoppingmall.common.factory;

import hyun6ik.shoppingmall.domain.delivery.entity.Delivery;

public class DeliveryFactory {

    public static Delivery delivery() {
        return Delivery.builder()
                .memberId(1L)
                .deliveryName("양천구")
                .deliveryFee(3000)
                .build();
    }
}
