package hyun6ik.shoppingmall.infrastructure.item.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import hyun6ik.shoppingmall.interfaces.main.dto.QMainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

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
}
