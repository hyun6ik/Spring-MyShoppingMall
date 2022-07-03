package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemImageRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemStore {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    public Item store(Item initItem) {
        return itemRepository.save(initItem);
    }

    public List<ItemImage> store(List<ItemImage> initItemImages) {
        return itemImageRepository.saveAll(initItemImages);
    }
}
