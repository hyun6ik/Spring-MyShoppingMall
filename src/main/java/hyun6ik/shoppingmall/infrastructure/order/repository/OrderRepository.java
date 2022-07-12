package hyun6ik.shoppingmall.infrastructure.order.repository;

import hyun6ik.shoppingmall.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
