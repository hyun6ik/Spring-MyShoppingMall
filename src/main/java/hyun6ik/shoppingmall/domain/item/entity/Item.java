package hyun6ik.shoppingmall.domain.item.entity;

import hyun6ik.shoppingmall.domain.base.BaseTimeEntity;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.global.utils.TokenGenerator;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

    private static final String PREFIX_ITEM = "item_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String itemName;

    @Column(nullable = false, length = 11)
    private Integer price;

    @Column(nullable = false, length = 11)
    private Integer stockNumber;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemSellStatus itemSellStatus;

    private String itemToken;

    private Long memberId;

    private Long deliveryId;

    @Embedded
    private ItemImages itemImages;

    @Builder
    public Item(String itemName, Integer price, Integer stockNumber, String itemDetail, ItemSellStatus itemSellStatus, Long memberId, Long deliveryId, ItemImages itemImages) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.memberId = memberId;
        this.deliveryId = deliveryId;

        addItemImages(itemImages);
    }

    private void addItemImages(ItemImages itemImages) {
        if (itemImages != null) {
            this.itemImages = itemImages;
            itemImages.belongTo(this);
            return;
        }
        this.itemImages = new ItemImages(new ArrayList<>());
    }

    public void update(Item updateInitItem, ItemImages updateItemImages) {
        this.itemName = updateInitItem.getItemName();
        this.price = updateInitItem.getPrice();
        this.stockNumber = updateInitItem.getStockNumber();
        this.itemDetail = updateInitItem.getItemDetail();
        this.itemSellStatus = updateInitItem.getItemSellStatus();
        this.memberId = updateInitItem.getMemberId();
        this.deliveryId = updateInitItem.getDeliveryId();
        this.itemImages.update(updateItemImages);
    }

    public void decreaseStock(int count) {
        this.stockNumber = stockNumber - count;
        if (stockNumber == 0) {
            this.itemSellStatus = ItemSellStatus.SOLD_OUT;
        }
    }

    public void increaseStock(int count) {
        this.stockNumber += count;
        if (stockNumber > 0) {
            this.itemSellStatus = ItemSellStatus.SELL;
        }
    }
}
