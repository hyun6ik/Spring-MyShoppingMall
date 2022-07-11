package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class UpdateItemDto {

    @NotNull
    private Long itemId;

    @NotBlank
    private String itemName;

    @NotNull
    private Integer price;

    @NotBlank
    private String itemDetail;

    @NotNull
    private Integer stockNumber;

    @NotNull
    private ItemSellStatus itemSellStatus;

    @NotNull
    private Long deliveryId;

    private List<MultipartFile> itemImageFiles;

    private List<ItemImageDto> itemImageDtos;

    private List<String> originalImageNames;


    @QueryProjection
    public UpdateItemDto(Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, Long deliveryId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.deliveryId = deliveryId;
    }

    public void addItemImagesDtos(List<ItemImageDto> itemImageDtos) {
        this.itemImageDtos = itemImageDtos;
    }

    @Getter @Setter
    public static class ItemImageDto {
        private Long itemImageId;
        private String originalImageName;

        @QueryProjection
        public ItemImageDto(Long itemImageId, String originalImageName) {
            this.itemImageId = itemImageId;
            this.originalImageName = originalImageName;
        }
    }

}