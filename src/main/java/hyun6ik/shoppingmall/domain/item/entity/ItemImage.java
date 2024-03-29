package hyun6ik.shoppingmall.domain.item.entity;

import hyun6ik.shoppingmall.domain.base.BaseTimeEntity;
import hyun6ik.shoppingmall.infrastructure.file.UploadFile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ItemImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String imageName;

    @Column(length = 500)
    private String imageUrl;

    @Column(length = 200)
    private String originalImageName;

    private Boolean isRepImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    @Builder
    public ItemImage(String imageName, String imageUrl, String originalImageName, boolean isRepImage) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.originalImageName = originalImageName;
        this.isRepImage = isRepImage;
    }

    public static ItemImage of(UploadFile uploadFile, Boolean isRepImage) {
        return ItemImage.builder()
                .imageName(uploadFile.getStoreFileName())
                .imageUrl(uploadFile.getFileUploadUrl())
                .originalImageName(uploadFile.getOriginalFileName())
                .isRepImage(isRepImage)
                .build();
    }

    public void update(UploadFile uploadFile) {
        this.imageName = uploadFile.getStoreFileName();
        this.imageUrl = uploadFile.getFileUploadUrl();
        this.originalImageName = uploadFile.getOriginalFileName();
    }

    public void clear() {
        this.imageName = null;
        this.imageUrl = null;
        this.originalImageName = null;
    }

    public void belongTo(Item item) {
        this.item = item;
    }

    public void update(ItemImage itemImage) {
        this.imageName = itemImage.getImageName();
        this.imageUrl = itemImage.getImageUrl();
        this.originalImageName = itemImage.getOriginalImageName();
        this.isRepImage = itemImage.getIsRepImage();
    }
}
