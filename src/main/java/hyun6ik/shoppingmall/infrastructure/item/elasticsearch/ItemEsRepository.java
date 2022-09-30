package hyun6ik.shoppingmall.infrastructure.item.elasticsearch;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemEsRepository extends ElasticsearchRepository<ItemDocument, Long> {
}
