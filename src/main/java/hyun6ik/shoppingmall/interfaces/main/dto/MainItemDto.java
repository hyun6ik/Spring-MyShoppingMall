package hyun6ik.shoppingmall.interfaces.main.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MainItemDto {

    private Long itemId;

    private String itemName;

    private String itemDetail;

    private String imageUrl;

    private Integer price;

    @QueryProjection
    public MainItemDto(Long itemId, String itemName, String itemDetail, String imageUrl, Integer price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}