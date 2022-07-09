package hyun6ik.shoppingmall.infrastructure.item.repository;

import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
}
