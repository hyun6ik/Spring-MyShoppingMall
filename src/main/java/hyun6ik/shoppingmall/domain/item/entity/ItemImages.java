package hyun6ik.shoppingmall.domain.item.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImages {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImage> itemImages = new ArrayList<>();

    public ItemImages(List<ItemImage> itemImages) {
        this.itemImages = itemImages;
    }

    public void belongTo(Item item) {
        itemImages.forEach(itemImage -> itemImage.belongTo(item));
    }
}
