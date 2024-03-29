package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemDocument;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.NotFoundException;
import hyun6ik.shoppingmall.infrastructure.item.elasticsearch.ItemEsQueryRepository;
import hyun6ik.shoppingmall.infrastructure.item.elasticsearch.ItemEsRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemQueryRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemImageDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemReader {

    private final ItemQueryRepository itemQueryRepository;
    private final ItemRepository itemRepository;
    private final ItemEsQueryRepository itemEsQueryRepository;
    private final ItemEsRepository itemEsRepository;

    public Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable) {
        return itemEsQueryRepository.searchMainItemsBy(searchQuery, pageable);
    }

    public ItemDtlDto getItemDtlDtoBy(Long itemId) {
        return itemQueryRepository.findItemDtlDtoBy(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ITEM));
    }

    public List<ItemDtlDto.ItemImageDto> getItemImageDtosBy(Long itemId) {
        return itemQueryRepository.findItemImageDtosBy(itemId);
    }

    public ItemRequestDto.Update getUpdateItemDtoBy(Long itemId, Long memberId) {
        return itemQueryRepository.findUpdateItemDtoBy(itemId, memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ITEM));
    }

    public List<ItemImageDto> getUpdateItemImageDtosBy(Long itemId) {
        final Optional<List<ItemImageDto>> updateItemImageDtos = itemQueryRepository.findUpdateItemImageDtosBy(itemId);

        if (updateItemImageDtos.isEmpty()) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_ITEM_IMAGE);
        }
        return updateItemImageDtos.get();
    }

    public Item getItemBy(Long itemId, Long memberId) {
        return itemRepository.findByIdAndMemberId(itemId, memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ITEM));
    }

    public Item getItemBy(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ITEM));
    }

    public ItemDocument getItemDocumentBy(Long itemId) {
        return itemEsRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ITEM));
    }
}
