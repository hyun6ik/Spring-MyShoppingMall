package hyun6ik.shoppingmall.infrastructure.order.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.QOrderHistDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.QOrderHistDto_OrderItemHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hyun6ik.shoppingmall.domain.delivery.entity.QDelivery.delivery;
import static hyun6ik.shoppingmall.domain.item.entity.QItem.item;
import static hyun6ik.shoppingmall.domain.item.entity.QItemImage.itemImage;
import static hyun6ik.shoppingmall.domain.order.entity.QOrder.order;
import static hyun6ik.shoppingmall.domain.order.entity.QOrderItem.orderItem;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<OrderHistDto> findOrderHistDtosBy(Long memberId, Pageable pageable) {
        final List<OrderHistDto> result = queryFactory
                .select(new QOrderHistDto(
                        order.id,
                        orderItem.item.id,
                        order.orderTime,
                        order.orderStatus,
                        orderItem.orderPrice,
                        getOrderDeliveryFee()
                ))
                .from(orderItem)
                .innerJoin(orderItem.order, order)
                .where(order.memberId.eq(memberId))
                .orderBy(order.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();

        final int size = queryFactory
                .selectFrom(order)
                .where(order.memberId.eq(memberId))
                .fetch()
                .size();

        return new PageImpl<>(result, pageable, size);
    }

    private JPQLQuery<Integer> getOrderDeliveryFee() {
        return JPAExpressions.select(delivery.deliveryFee)
                .from(delivery)
                .where(delivery.id.eq(JPAExpressions.select(item.deliveryId)
                        .from(item)
                        .where(item.id.eq(orderItem.item.id))));
    }

    public List<OrderHistDto.OrderItemHistDto> findOrderHistItemDtosBy(Long orderId, Long itemId) {
        return queryFactory
                .select(new QOrderHistDto_OrderItemHistDto(
                        getItemName(itemId),
                        orderItem.count,
                        orderItem.orderPrice,
                        getItemRepImage(itemId)
                ))
                .from(orderItem)
                .where(orderItem.order.id.eq(orderId))
                .fetch();

    }

    private JPQLQuery<String> getItemName(Long itemId) {
        return JPAExpressions.
                select(item.itemName)
                .from(item)
                .where(item.id.eq(itemId));
    }

    private JPQLQuery<String> getItemRepImage(Long itemId) {
        return JPAExpressions.
                select(itemImage.imageUrl)
                .from(itemImage)
                .innerJoin(itemImage.item, item)
                .where(itemImage.isRepImage.isTrue(), itemImage.item.id.eq(itemId));
    }
}
