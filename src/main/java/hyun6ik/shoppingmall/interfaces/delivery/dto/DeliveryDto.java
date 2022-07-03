package hyun6ik.shoppingmall.interfaces.delivery.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDto {

    private Long deliveryId;
    private Integer deliveryFee;
    private String deliveryName;

    @QueryProjection
    public DeliveryDto(Long deliveryId, Integer deliveryFee, String deliveryName) {
        this.deliveryId = deliveryId;
        this.deliveryFee = deliveryFee;
        this.deliveryName = deliveryName;
    }
}
