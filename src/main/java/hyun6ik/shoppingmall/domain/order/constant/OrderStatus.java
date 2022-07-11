package hyun6ik.shoppingmall.domain.order.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    ORDER("주문완료"),CANCEL("주문취소");

    private final String description;

}
