package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
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

    @Builder
    public ItemImageDto(Long itemImageId, String imageName, String imageUrl, String originalImageName, Boolean isRepImage) {
        this.itemImageId = itemImageId;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.originalImageName = originalImageName;
        this.isRepImage = isRepImage;
    }
}
