package hyun6ik.shoppingmall.global.config.elasticsearch;

import hyun6ik.shoppingmall.infrastructure.item.elasticsearch.ItemEsRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackageClasses = ItemEsRepository.class)
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    private final String elasticSearchHost;

    public ElasticSearchConfig(@Value("${elasticsearch.host}") String elasticSearchHost) {
        this.elasticSearchHost = elasticSearchHost;
    }

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticSearchHost)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
