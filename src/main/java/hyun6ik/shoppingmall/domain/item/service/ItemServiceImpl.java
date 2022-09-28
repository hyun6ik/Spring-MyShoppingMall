package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.domain.item.entity.ItemImages;
import hyun6ik.shoppingmall.domain.order.entity.OrderItem;
import hyun6ik.shoppingmall.global.annotation.LogTrace;
import hyun6ik.shoppingmall.infrastructure.item.ItemDtoMapper;
import hyun6ik.shoppingmall.infrastructure.item.ItemFactory;
import hyun6ik.shoppingmall.infrastructure.item.ItemReader;
import hyun6ik.shoppingmall.infrastructure.item.ItemStore;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemImageDto;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
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
    public ItemResponseDto createItem(ItemRequestDto.Insert request, Long memberId) {
        final ItemImages initItemImages = itemFactory.createItemImages(request.getItemImageFiles());
        final Item initItem = request.toEntity(memberId, initItemImages);

        final Item item = itemStore.store(initItem);

        return itemDtoMapper.of(item);
    }

    @Override
    @LogTrace
    public Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable) {
        return itemReader.getMainItemsBy(searchQuery, pageable);
    }

    @Override
    public ItemDtlDto getItemDtlBy(Long itemId) {
        final ItemDtlDto itemDtlDto = itemReader.getItemDtlDtoBy(itemId);
        final List<ItemDtlDto.ItemImageDto> itemImagesDtos = itemReader.getItemImageDtosBy(itemId);
        itemDtlDto.addItemImageDtos(itemImagesDtos);
        return itemDtlDto;
    }

    @Override
    public ItemRequestDto.Update getUpdateItemDtoBy(Long itemId, Long memberId) {
        final ItemRequestDto.Update updateItemDto = itemReader.getUpdateItemDtoBy(itemId, memberId);
        final List<ItemImageDto> updateItemImageDtos = itemReader.getUpdateItemImageDtosBy(itemId);
        updateItemDto.addItemImageDtos(updateItemImageDtos);
        return updateItemDto;
    }

    @Override
    @Transactional
    public ItemResponseDto updateItem(Long itemId, Long memberId, ItemRequestDto.Update request) {
        final Item item = itemReader.getItemBy(itemId, memberId);
        final List<ItemImage> itemImages = itemReader.getItemImagesBy(itemId);

        final Item updateItem = request.toEntity(memberId);
        item.update(updateItem);

        itemFactory.updateItemImages(request, itemImages);

        return itemDtoMapper.of(item);
    }

    @Override
    public Item getItemBy(Long itemId) {
        return itemReader.getItemBy(itemId);
    }

    @Override
    @Transactional
    public void increaseStock(OrderItem cancelOrderItem) {
        final Item item = itemReader.getItemBy(cancelOrderItem.getItemId());
        item.increaseStock(cancelOrderItem.getCount());
    }
}
