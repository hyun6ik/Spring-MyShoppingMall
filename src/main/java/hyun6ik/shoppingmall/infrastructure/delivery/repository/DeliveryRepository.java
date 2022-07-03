package hyun6ik.shoppingmall.infrastructure.delivery.repository;

import hyun6ik.shoppingmall.domain.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
