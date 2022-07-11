package hyun6ik.shoppingmall.infrastructure.item.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.QUpdateItemDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.QUpdateItemDto_ItemImageDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.UpdateItemDto;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import hyun6ik.shoppingmall.interfaces.item.dto.QItemDtlDto;
import hyun6ik.shoppingmall.interfaces.item.dto.QItemDtlDto_ItemImageDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import hyun6ik.shoppingmall.interfaces.main.dto.QMainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static hyun6ik.shoppingmall.domain.delivery.entity.QDelivery.*;
import static hyun6ik.shoppingmall.domain.item.entity.QItem.item;
import static hyun6ik.shoppingmall.domain.item.entity.QItemImage.itemImage;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<MainItemDto> findMainItemsBySearchOptionWithPaging(String searchQuery, Pageable pageable) {
        final BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(searchQuery)) {
            builder.or(item.itemDetail.containsIgnoreCase(searchQuery));
            builder.or(item.itemName.containsIgnoreCase(searchQuery));
        }

        final List<MainItemDto> content = queryFactory
                .select(new QMainItemDto(
                        item.id,
                        item.itemName,
                        item.itemDetail,
                        itemImage.imageUrl,
                        item.price
                ))
                .from(itemImage)
                .leftJoin(itemImage.item, item)
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .where(itemImage.isRepImage.isTrue(), item.itemSellStatus.eq(ItemSellStatus.SELL), builder)
                .fetch();

        final int size = queryFactory
                .selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, size);
    }

    public Optional<ItemDtlDto> findItemDtlDtoBy(Long itemId) {
        return Optional.ofNullable(queryFactory
                .select(new QItemDtlDto(
                        item.id.as("itemId"),
                        item.itemName.as("itemName"),
                        item.price.as("price"),
                        item.itemDetail.as("itemDetail"),
                        item.stockNumber.as("stockNumber"),
                        item.itemSellStatus.as("itemSellStatus"),
                        JPAExpressions.select(delivery.deliveryFee)
                                .from(delivery)
                                .where(delivery.id.eq(JPAExpressions.select(item.deliveryId)
                                        .from(item)
                                        .where(item.id.eq(itemId))))
                ))
                .from(item)
                .where(item.id.eq(itemId))
                .fetchOne());
    }

    public List<ItemDtlDto.ItemImageDto> findItemImageDtosBy(Long itemId) {
        return queryFactory
                .select(new QItemDtlDto_ItemImageDto(
                        itemImage.imageUrl
                ))
                .from(itemImage)
                .where(itemImage.item.id.eq(itemId), itemImage.imageName.isNotNull())
                .orderBy(itemImage.id.asc())
                .fetch();
    }

    public Optional<UpdateItemDto> findUpdateItemDtoBy(Long itemId, Long memberId) {
        return Optional.ofNullable(queryFactory
                .select(new QUpdateItemDto(
                        item.id,
                        item.itemName,
                        item.price,
                        item.itemDetail,
                        item.stockNumber,
                        item.itemSellStatus,
                        item.deliveryId
                ))
                .from(item)
                .where(item.id.eq(itemId), item.memberId.eq(memberId))
                .fetchOne());
    }

    public Optional<List<UpdateItemDto.ItemImageDto>>  findUpdateItemImageDtosBy(Long itemId) {
        return Optional.ofNullable(queryFactory
                .select(new QUpdateItemDto_ItemImageDto(
                        itemImage.id,
                        itemImage.originalImageName
                ))
                .from(itemImage)
                .innerJoin(itemImage.item, item)
                .where(itemImage.item.id.eq(itemId))
                .fetch());
    }
}
