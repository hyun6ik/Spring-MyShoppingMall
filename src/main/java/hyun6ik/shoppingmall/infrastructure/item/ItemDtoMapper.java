package hyun6ik.shoppingmall.infrastructure.item;

import hyun6ik.shoppingmall.domain.item.entity.Item;
import hyun6ik.shoppingmall.domain.item.entity.ItemImage;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.InsertItemDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {


    Item toEntity(InsertItemDto.Request request, Long memberId);


    @Mappings({@Mapping(source = "item.id", target = "itemId"), @Mapping(source = "itemImages", target = "itemImageDtos")})
    InsertItemDto.Response of(Item item, List<ItemImage> itemImages);


}
