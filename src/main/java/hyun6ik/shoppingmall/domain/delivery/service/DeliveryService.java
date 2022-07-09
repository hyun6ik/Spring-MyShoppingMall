package hyun6ik.shoppingmall.domain.delivery.service;

import hyun6ik.shoppingmall.domain.delivery.entity.Delivery;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;

import java.util.List;

public interface DeliveryService {

    Delivery getDeliveryBy(Long deliveryId);

    List<DeliveryDto> getDeliveryDtosBy(Long memberId);
}
