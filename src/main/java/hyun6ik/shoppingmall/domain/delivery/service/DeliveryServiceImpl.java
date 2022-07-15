package hyun6ik.shoppingmall.domain.delivery.service;

import hyun6ik.shoppingmall.infrastructure.delivery.DeliveryReader;
import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryReader deliveryReader;

    @Override
    public List<DeliveryDto> getDeliveryDtosBy(Long memberId) {
        return deliveryReader.getDeliveryDtosBy(memberId);
    }

}
