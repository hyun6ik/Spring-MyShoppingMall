package hyun6ik.shoppingmall.domain.delivery.service;

import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;

import java.util.List;

public interface DeliveryService {

    List<DeliveryDto> getDeliveryDtosBy(Long memberId);
}
