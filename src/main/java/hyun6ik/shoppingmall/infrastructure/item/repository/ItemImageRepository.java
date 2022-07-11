package hyun6ik.shoppingmall.infrastructure.item.repository;

import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
    Optional<List<ItemImage>> findAllByItemId(Long itemId);
}
