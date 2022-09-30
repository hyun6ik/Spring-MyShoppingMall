package hyun6ik.shoppingmall.infrastructure.item;


import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

    @Mappings({@Mapping(source = "item.id", target = "itemId"), @Mapping(source = "item.itemImages.itemImages", target = "itemImageDtos")})
    ItemResponseDto of(Item item);
}
