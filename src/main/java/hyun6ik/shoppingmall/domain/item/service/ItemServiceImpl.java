package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.infrastructure.item.ItemDtoMapper;
import hyun6ik.shoppingmall.infrastructure.item.ItemFactory;
import hyun6ik.shoppingmall.infrastructure.item.ItemReader;
import hyun6ik.shoppingmall.infrastructure.item.ItemStore;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.InsertItemDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemReader itemReader;
    private final ItemStore itemStore;
    private final ItemFactory itemFactory;
    private final ItemDtoMapper itemDtoMapper;


    @Override
    @Transactional
    public InsertItemDto.Response createItem(InsertItemDto.Request request, Long memberId) {
        final Item initItem = itemDtoMapper.toEntity(request, memberId);
        final List<ItemImage> initItemImages = itemFactory.createItemImages(initItem, request.getItemImageFiles());

        final Item item = itemStore.store(initItem);
        final List<ItemImage> itemImages = itemStore.store(initItemImages);

        return itemDtoMapper.of(item, itemImages);
    }

    @Override
    public Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable) {
        return itemReader.getMainItemsBy(searchQuery, pageable);

    }
}
