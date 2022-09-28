package hyun6ik.shoppingmall.domain.item.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Getter
@Document(indexName = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDocument {

    @Id
    private Long id;

    private String itemName;

    private String itemDetail;

    @Builder
    public ItemDocument(Long id, String itemName, String itemDetail) {
        this.id = id;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
    }

    public static ItemDocument of(Item item) {
        return ItemDocument.builder()
                .id(item.getId())
                .itemDetail(item.getItemDetail())
                .itemName(item.getItemName())
                .build();
    }

    public void update(Item item) {
        this.itemName = item.getItemName();
        this.itemDetail = item.getItemDetail();
    }
}
