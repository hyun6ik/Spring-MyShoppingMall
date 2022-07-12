package hyun6ik.shoppingmall.infrastructure.order;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.OrderValidException;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    public void validateStock(Item item, Integer count) {
        if (item.getStockNumber() - count < 0) {
            throw new OrderValidException(ErrorCode.NOT_ENOUGH_STOCK, String.format("(현재 재고 수량: %s)", item.getStockNumber()));
        }
    }
}
