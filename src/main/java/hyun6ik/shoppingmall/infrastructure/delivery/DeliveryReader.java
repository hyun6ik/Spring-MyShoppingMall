package hyun6ik.shoppingmall.infrastructure.delivery;

import hyun6ik.shoppingmall.domain.delivery.entity.Delivery;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.global.exception.BusinessException;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.infrastructure.delivery.repository.DeliveryQueryRepository;
import hyun6ik.shoppingmall.infrastructure.delivery.repository.DeliveryRepository;
import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveryReader {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryQueryRepository deliveryQueryRepository;


    public Delivery getDeliveryBy(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_DELIVERY));
    }


    public List<DeliveryDto> getDeliveryDtosBy(Long memberId) {
        return deliveryQueryRepository.findDeliveryDtos(memberId);
    }
}
