package hyun6ik.shoppingmall.interfaces.item.controller;

import hyun6ik.shoppingmall.application.order.OrderFacade;
import hyun6ik.shoppingmall.domain.item.service.ItemService;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import hyun6ik.shoppingmall.interfaces.item.dto.ItemDtlDto;
import hyun6ik.shoppingmall.interfaces.item.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/itemdtl")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final OrderFacade orderFacade;

    @GetMapping( "/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemDtlDto itemDtlDto = itemService.getItemDtlBy(itemId);
        model.addAttribute("item", itemDtlDto);
        return "item/itemdtl";
    }

    @ResponseBody
    @PostMapping("/order")
    public ResponseEntity<OrderDto.Response> orderItem(@Valid @RequestBody OrderDto.Request request, @MemberId Long memberId) {
        return ResponseEntity.ok(orderFacade.createOrder(request, memberId));
    }
}
