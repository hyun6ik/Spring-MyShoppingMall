package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.interfaces.adminItem.dto.InsertItemDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ItemService {

    InsertItemDto.Response createItem(InsertItemDto.Request request, Long memberId) throws IOException;

    Page<MainItemDto> getMainItemsBy(String searchQuery, Pageable pageable);
}
