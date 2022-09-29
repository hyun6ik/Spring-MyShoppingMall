package hyun6ik.shoppingmall.domain.item.service;

import hyun6ik.shoppingmall.common.factory.DeliveryFactory;
import hyun6ik.shoppingmall.common.factory.ItemFactory;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.infrastructure.delivery.repository.DeliveryRepository;
import hyun6ik.shoppingmall.infrastructure.item.elasticsearch.ItemEsRepository;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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

   @Autowired
   private MemberRepository memberRepository;

   @AfterEach
   public void clear() {
       itemRepository.deleteAll();
       itemEsRepository.deleteAll();
       memberRepository.deleteAll();
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

    @Test
    @Transactional
    @DisplayName("[상품 조회하기]")
    void getItem_success() {
        //given
        final Item item = itemRepository.save(ItemFactory.item());
        //when
        final Item findItem = itemService.getItemBy(item.getId());
        //then
        assertThat(findItem.getId()).isEqualTo(item.getId());
        assertThat(findItem.getItemName()).isEqualTo(item.getItemName());
        assertThat(findItem.getItemDetail()).isEqualTo(item.getItemDetail());
        assertThat(findItem.getItemSellStatus()).isEqualTo(item.getItemSellStatus());
        assertThat(findItem.getPrice()).isEqualTo(item.getPrice());
        assertThat(findItem.getStockNumber()).isEqualTo(item.getStockNumber());
        assertThat(findItem.getItemImages().getItemImages().size()).isEqualTo(item.getItemImages().getItemImages().size());
    }

    @DisplayName("[메인 페이지]")
    @Nested
    class MainPage {
        @Test
        @DisplayName("- 상품 조회 페이징 처리")
        void getMainItem_success() {
            //given
            for (int i = 0; i < 10; i++) {
                final Item item = itemRepository.save(ItemFactory.item());
                itemEsRepository.save(ItemFactory.itemDocument(item));
            }
            Pageable pageable = PageRequest.of(0,6);
            //when
            final Page<MainItemDto> mainItems = itemService.getMainItemsBy("", pageable);
            //then
            assertThat(mainItems.getTotalPages()).isEqualTo(2);
            assertThat(mainItems.getTotalElements()).isEqualTo(10);
            assertThat(mainItems.getSize()).isEqualTo(6);
            assertThat(mainItems.isFirst()).isTrue();
            assertThat(mainItems.hasNext()).isTrue();
        }

        @Test
        @DisplayName("- 상품 검색 : [itemName]")
        void getMainItem_success2() {
            //given
            for (int i = 0; i < 10; i++) {
                final Item item = itemRepository.save(ItemFactory.item());
                itemEsRepository.save(ItemFactory.itemDocument(item));
            }
            final Item anotherItem = itemRepository.save(Item.builder()
                    .itemName("이름입니다")
                    .itemDetail("이상한상세설명")
                    .itemImages(ItemFactory.itemImages(1))
                    .stockNumber(10)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .price(1000)
                    .memberId(1L)
                    .deliveryId(1L)
                    .build());
            itemEsRepository.save(ItemFactory.itemDocument(anotherItem));

            Pageable pageable = PageRequest.of(0,6);
            //when
            final Page<MainItemDto> mainItems = itemService.getMainItemsBy("이름", pageable);
            //then
            assertThat(mainItems.getContent().size()).isEqualTo(1);
            assertThat(mainItems.getContent().get(0).getItemName()).isEqualTo(anotherItem.getItemName());
            assertThat(mainItems.getContent().get(0).getItemDetail()).isEqualTo(anotherItem.getItemDetail());
        }

        @Test
        @DisplayName("- 상품 검색 : [itemDetail]")
        void getMainItem_success3() {
            //given
            for (int i = 0; i < 3; i++) {
                final Item item = itemRepository.save(ItemFactory.item());
                itemEsRepository.save(ItemFactory.itemDocument(item));
            }
            for (int i = 1; i <= 3; i++) {
                final Item anotherItem = itemRepository.save(Item.builder()
                        .itemName("이름입니다" + i)
                        .itemDetail("이상한상세설명" + i)
                        .itemImages(ItemFactory.itemImages(1))
                        .stockNumber(10)
                        .itemSellStatus(ItemSellStatus.SELL)
                        .price(1000)
                        .memberId(1L)
                        .deliveryId(1L)
                        .build());
                itemEsRepository.save(ItemFactory.itemDocument(anotherItem));
            }

            Pageable pageable = PageRequest.of(0,6);
            //when
            final Page<MainItemDto> mainItems = itemService.getMainItemsBy("이상한", pageable);
            //then
            assertThat(mainItems.getContent().size()).isEqualTo(3);
            assertThat(mainItems.getContent().get(0).getItemName()).isEqualTo("이름입니다3");
            assertThat(mainItems.getContent().get(0).getItemDetail()).isEqualTo("이상한상세설명3");
        }
    }


}
