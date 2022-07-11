package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImageDto {

    private Long itemImageId;
    private String imageName;
    private String imageUrl;
    private String originalImageName;
    private Boolean isRepImage;

    @QueryProjection
    public ItemImageDto(Long itemImageId, String originalImageName) {
        this.itemImageId = itemImageId;
        this.originalImageName = originalImageName;
    }
}
