package hyun6ik.shoppingmall.infrastructure.order.repository;

import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
