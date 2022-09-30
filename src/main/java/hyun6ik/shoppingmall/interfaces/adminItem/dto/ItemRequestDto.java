package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImages;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ItemRequestDto {

    @Getter @Setter
    public static class Insert {
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

        public Insert() {

        }

        @Builder
        public Insert(String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, Long deliveryId, List<MultipartFile> itemImageFiles) {
            this.itemName = itemName;
            this.price = price;
            this.itemDetail = itemDetail;
            this.stockNumber = stockNumber;
            this.itemSellStatus = itemSellStatus;
            this.deliveryId = deliveryId;
            this.itemImageFiles = itemImageFiles;
        }

        public Item toEntity(Long memberId, ItemImages initItemImages) {
            return Item.builder()
                    .itemName(itemName)
                    .price(price)
                    .itemDetail(itemDetail)
                    .stockNumber(stockNumber)
                    .itemSellStatus(itemSellStatus)
                    .deliveryId(deliveryId)
                    .memberId(memberId)
                    .itemImages(initItemImages)
                    .build();
        }
    }

    @Getter @Setter
    @ToString
    public static class Update {
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
        @Builder
        public Update(Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, Long deliveryId) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.price = price;
            this.itemDetail = itemDetail;
            this.stockNumber = stockNumber;
            this.itemSellStatus = itemSellStatus;
            this.deliveryId = deliveryId;
        }

        public void addItemImageDtos(List<ItemImageDto> itemImageDtos) {
            this.itemImageDtos = itemImageDtos;
        }

        public void addOriginalImageNames(List<String> originalImageNames) {
            this.originalImageNames = originalImageNames;
        }

        public void addMultipartFile(List<MultipartFile> itemImageFiles) {
            this.itemImageFiles = itemImageFiles;
        }

        public Item toEntity(Long memberId) {
            return Item.builder()
                    .itemName(itemName)
                    .price(price)
                    .itemDetail(itemDetail)
                    .stockNumber(stockNumber)
                    .itemSellStatus(itemSellStatus)
                    .deliveryId(deliveryId)
                    .memberId(memberId)
                    .build();
        }

    }
}
