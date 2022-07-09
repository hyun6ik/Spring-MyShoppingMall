package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.NotFoundException;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemQueryRepository;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemReader {

    private final ItemQueryRepository itemQueryRepository;

    public Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable) {
        return itemQueryRepository.findMainItemsBySearchOptionWithPaging(searchQuery, pageable);
    }

    public ItemDtlDto getItemDtlDtoBy(Long itemId) {
        return itemQueryRepository.findItemDtlDtoBy(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ITEM));
    }

    public List<ItemDtlDto.ItemImageDto> getItemImageDtosBy(Long itemId) {
        return itemQueryRepository.findItemImageDtosBy(itemId);
    }
}
