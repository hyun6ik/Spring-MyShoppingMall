package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.infrastructure.item.repository.ItemQueryRepository;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemReader {

    private final ItemQueryRepository itemQueryRepository;

    public Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable) {
        return itemQueryRepository.findMainItemsBySearchOptionWithPaging(searchQuery, pageable);
    }
}
