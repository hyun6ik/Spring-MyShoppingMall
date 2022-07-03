package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.interfaces.adminItem.dto.InsertItemDto;

import java.io.IOException;

public interface ItemService {

    InsertItemDto.Response createItem(InsertItemDto.Request request, Long memberId) throws IOException;
}
