package hyun6ik.shoppingmall.interfaces.item.controller;

import hyun6ik.shoppingmall.domain.item.service.ItemService;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itemdtl")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping( "/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemDtlDto itemDtlDto = itemService.getItemDtlBy(itemId);
        model.addAttribute("item", itemDtlDto);
        return "item/itemdtl";
    }
}
