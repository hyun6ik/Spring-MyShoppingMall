package hyun6ik.shoppingmall.interfaces.delivery.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDto {

    private Integer deliveryFee;
    private String deliveryName;

    @QueryProjection
    public DeliveryDto(Integer deliveryFee, String deliveryName) {
        this.deliveryFee = deliveryFee;
        this.deliveryName = deliveryName;
    }
}
