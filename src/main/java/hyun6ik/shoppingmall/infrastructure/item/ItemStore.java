package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.infrastructure.item.elasticsearch.ItemEsRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemImageRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class ItemStore {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final ItemEsRepository itemEsRepository;

    public Item store(Item initItem) {
        final Item item = itemRepository.save(initItem);
        itemEsRepository.save(item);
        return item;
    }

    public List<ItemImage> store(List<ItemImage> initItemImages) {
        return itemImageRepository.saveAll(initItemImages);
    }
}
