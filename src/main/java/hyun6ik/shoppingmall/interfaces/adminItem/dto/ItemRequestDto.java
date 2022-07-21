package hyun6ik.shoppingmall.interfaces.adminItem.dto;

import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.shoppingmall.domain.item.constant.ItemSellStatus;
import hyun6ik.shoppingmall.domain.item.entity.Item;
import lombok.Getter;
import lombok.Setter;
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

    @Getter @Setter
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
