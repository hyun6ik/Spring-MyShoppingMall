package hyun6ik.shoppingmall.infrastructure.delivery;

import hyun6ik.shoppingmall.infrastructure.delivery.repository.DeliveryQueryRepository;
import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeliveryReader {

    private final DeliveryQueryRepository deliveryQueryRepository;

    public List<DeliveryDto> getDeliveryDtosBy(Long memberId) {
        return deliveryQueryRepository.findDeliveryDtos(memberId);
    }
}
