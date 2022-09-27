package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.common.factory.ItemFactory;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ItemServiceTest {

   @Autowired
   private ItemService itemService;

    @Test
    @DisplayName("[상품 등록하기] - 성공")
    void createItem_success() throws IOException {
        //given
        final ItemRequestDto.Insert request = ItemFactory.getItemInsertRequestDto();
        final Long memberId = 1L;

        //when
        final ItemResponseDto response = itemService.createItem(request, memberId);
        //then
        assertThat(response.getItemName()).isEqualTo("상품1");
    }

}
