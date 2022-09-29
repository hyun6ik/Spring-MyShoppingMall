package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.common.factory.DeliveryFactory;
import hyun6ik.shoppingmall.common.factory.ItemFactory;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.infrastructure.delivery.repository.DeliveryRepository;
import hyun6ik.shoppingmall.infrastructure.item.elasticsearch.ItemEsRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemImageRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class ItemServiceTest {

   @Autowired
   private ItemService itemService;

   @Autowired
   private ItemRepository itemRepository;

   @Autowired
   private ItemEsRepository itemEsRepository;

   @Autowired
   private DeliveryRepository deliveryRepository;

   @AfterEach
   public void clear() {
       itemRepository.deleteAll();
       itemEsRepository.deleteAll();
   }

    @Test
    @DisplayName("[상품 등록하기]")
    void createItem_success() throws IOException {
        //given
        final ItemRequestDto.Insert request = ItemFactory.getItemInsertRequestDto();
        final Long memberId = 1L;

        //when
        final ItemResponseDto response = itemService.createItem(request, memberId);
        //then
        assertThat(response.getItemName()).isEqualTo("상품1");
        assertThat(response.getItemDetail()).isEqualTo("상세설명");
        assertThat(response.getItemSellStatus()).isEqualTo(ItemSellStatus.SELL);
        assertThat(response.getDeliveryId()).isEqualTo(1L);
        assertThat(response.getPrice()).isEqualTo(10000);
        assertThat(response.getStockNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("[상품 수정하기]")
    void updateItem_success() throws IOException {
        //given
        final ItemRequestDto.Update request = ItemFactory.getItemUpdateRequestDto();
        final Item item = itemRepository.save(ItemFactory.item());
        itemEsRepository.save(ItemFactory.itemDocument(item));

        //when
        final ItemResponseDto response = itemService.updateItem(item.getId(), item.getMemberId(), request);
        //then
        assertThat(response.getItemName()).isEqualTo("수정");
        assertThat(response.getItemDetail()).isEqualTo("수정상세");
        assertThat(response.getItemSellStatus()).isEqualTo(ItemSellStatus.SELL);
        assertThat(response.getDeliveryId()).isEqualTo(2L);
        assertThat(response.getPrice()).isEqualTo(20000);
        assertThat(response.getStockNumber()).isEqualTo(20);
        assertThat(response.getItemImageDtos().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("[상품 상세보기]")
    void getItemDtl_success() {
        //given
        deliveryRepository.save(DeliveryFactory.delivery());
        final Item item = itemRepository.save(ItemFactory.item());
        //when
        final ItemDtlDto response = itemService.getItemDtlBy(item.getId());
        //then
        assertThat(response.getItemName()).isEqualTo("상품1");
        assertThat(response.getItemDetail()).isEqualTo("상세설명");
        assertThat(response.getItemSellStatus()).isEqualTo(ItemSellStatus.SELL);
        assertThat(response.getDeliveryFee()).isEqualTo(3000);
        assertThat(response.getPrice()).isEqualTo(10000);
        assertThat(response.getStockNumber()).isEqualTo(10);

    }
}
