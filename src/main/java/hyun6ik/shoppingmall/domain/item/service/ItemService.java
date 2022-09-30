package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.order.entity.OrderItems;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ItemService {

    ItemResponseDto createItem(ItemRequestDto.Insert request, Long memberId) throws IOException;

    Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable);

    ItemDtlDto getItemDtlBy(Long itemId);

    ItemRequestDto.Update getUpdateItemDtoBy(Long itemId, Long memberId);

    ItemResponseDto updateItem(Long itemId, Long memberId, ItemRequestDto.Update request);

    Item getItemBy(Long itemId);

    void increaseStock(OrderItems cancelOrderItem);
}
