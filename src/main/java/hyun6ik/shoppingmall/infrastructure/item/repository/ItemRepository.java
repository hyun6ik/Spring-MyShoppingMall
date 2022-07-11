package hyun6ik.shoppingmall.infrastructure.item.repository;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndMemberId(Long itemId, Long memberId);
}
