package hyun6ik.shoppingmall.infrastructure.item.elasticsearch;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemDocument;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import hyun6ik.shoppingmall.interfaces.main.dto.QMainItemDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static hyun6ik.shoppingmall.domain.item.entity.QItem.*;
import static hyun6ik.shoppingmall.domain.item.entity.QItemImage.*;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Repository
@RequiredArgsConstructor
public class ItemEsQueryRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private final JPAQueryFactory queryFactory;

    public Page<MainItemDto> searchMainItemsBy(String searchQuery, Pageable pageable) {
        String fixedQuery = "";
        if (!"".equals(searchQuery)) {
            fixedQuery = findQuotes(searchQuery);
        }

        final Criteria criteria = Criteria.where("itemName").expression("*" + fixedQuery + "*")
                .or("itemDetail").expression("*" + fixedQuery + "*");

        final Query query = new CriteriaQuery(criteria);
        final SearchHits<ItemDocument> search = elasticsearchOperations.search(query, ItemDocument.class);
        final List<Long> ids = search.stream()
                .map(SearchHit::getContent)
                .map(ItemDocument::getId)
                .collect(Collectors.toList());
        return findAllByIds(ids, pageable);
    }

    private String findQuotes(String searchQuery) {
        final StringBuilder builder = new StringBuilder();
        for (char c : searchQuery.toCharArray()) {
            if (c == '"') {
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    private Page<MainItemDto> findAllByIds(List<Long> ids, Pageable pageable) {
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
                .where(item.id.in(ids), itemImage.isRepImage.isTrue(), item.itemSellStatus.eq(ItemSellStatus.SELL))
                .fetch();

        final int size = ids.size();

        return new PageImpl<>(content, pageable, size);
    }
}
