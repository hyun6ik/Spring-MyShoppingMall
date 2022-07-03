package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.infrastructure.item.ItemDtoMapper;
import hyun6ik.shoppingmall.infrastructure.item.ItemFactory;
import hyun6ik.shoppingmall.infrastructure.item.ItemReader;
import hyun6ik.shoppingmall.infrastructure.item.ItemStore;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.InsertItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemReader itemReader;
    private final ItemStore itemStore;
    private final ItemFactory itemFactory;
    private final ItemDtoMapper itemDtoMapper;


    @Override
    public InsertItemDto.Response createItem(InsertItemDto.Request request, Long memberId) throws IOException {
        final Item initItem = itemDtoMapper.toEntity(request, memberId);
        final List<ItemImage> initItemImages = itemFactory.createItemImages(initItem, request.getItemImageFiles());

        final Item item = itemStore.store(initItem);
        final List<ItemImage> itemImages = itemStore.store(initItemImages);

        return itemDtoMapper.of(item, itemImages);
    }
}
