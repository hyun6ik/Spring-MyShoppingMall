package hyun6ik.shoppingmall.domain.order.service;

import hyun6ik.shoppingmall.common.factory.ItemFactory;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.order.constant.OrderStatus;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.OrderValidException;
import hyun6ik.shoppingmall.infrastructure.item.repository.ItemRepository;
import hyun6ik.shoppingmall.infrastructure.order.repository.OrderRepository;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @AfterEach
    public void clear() {
        orderRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Nested
    @DisplayName("[상품 주문]")
    class CreateOrder {

        @Test
        @DisplayName("- 성공")
        void createOrder_success() {
            //given
            final Item item = itemRepository.save(ItemFactory.item());
            Long memberId = 1L;
            int count = 8;
            //when
            final OrderDto.Response response = orderService.createOrder(item, memberId, count);
            //then
            assertThat(response.getMemberId()).isEqualTo(1L);
            assertThat(response.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
            assertThat(response.getOrderItemDtos().get(0).getOrderPrice()).isEqualTo(80000);
            assertThat(response.getOrderItemDtos().get(0).getCount()).isEqualTo(count);
        }

        @Test
        @DisplayName("- 준비된 수량보다 많이 준비하면 예외 발생")
        void createOrder_fail() {
            //given
            final Item item = itemRepository.save(ItemFactory.item());
            Long memberId = 1L;
            int count = 20;
            //when && then
            assertThatThrownBy(() -> orderService.createOrder(item, memberId, count))
                    .isInstanceOf(OrderValidException.class)
                    .hasMessage(ErrorCode.NOT_ENOUGH_STOCK.getMessage() + String.format("(현재 재고 수량: %s)", item.getStockNumber()));
        }
    }

}
