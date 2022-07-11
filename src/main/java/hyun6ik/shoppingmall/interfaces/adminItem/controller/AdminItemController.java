package hyun6ik.shoppingmall.interfaces.adminItem.controller;

import hyun6ik.shoppingmall.domain.delivery.service.DeliveryService;
import hyun6ik.shoppingmall.domain.item.service.ItemService;
import hyun6ik.shoppingmall.global.annotation.AdminUser;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.ValidException;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemRequestDto;
import hyun6ik.shoppingmall.interfaces.adminItem.dto.ItemResponseDto;
import hyun6ik.shoppingmall.interfaces.delivery.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/items")
@RequiredArgsConstructor
public class AdminItemController {

    private final ItemService itemService;
    private final DeliveryService deliveryService;

    @AdminUser
    @GetMapping("/new")
    public String itemForm(Model model, @MemberId Long memberId) {

        final List<DeliveryDto> deliveryDtos = deliveryService.getDeliveryDtosBy(memberId);

        model.addAttribute("deliveryDtos", deliveryDtos);
        model.addAttribute("insertItemDto", new ItemRequestDto.Insert());

        return "adminitem/registeritemform";
    }

    @AdminUser
    @PostMapping("/new")
    public String createItem(@Valid @ModelAttribute("insertItemDto") ItemRequestDto.Insert request, BindingResult bindingResult,
                             @MemberId Long memberId, RedirectAttributes redirectAttributes, Model model) throws IOException {

        if (request.getItemImageFiles().get(0).isEmpty()) {
            bindingResult.reject("NOT_UPLOAD_REP_IMAGE", ErrorCode.NOT_UPLOAD_REP_IMAGE.getMessage());
        }

        if (bindingResult.hasErrors()) {
            final List<DeliveryDto> deliveryDtos = deliveryService.getDeliveryDtosBy(memberId);
            model.addAttribute("deliveryDtos", deliveryDtos);

            return "adminitem/registeritemform";
        }

        try {
            final ItemResponseDto response = itemService.createItem(request, memberId);
            redirectAttributes.addAttribute("itemId", response.getItemId());
        } catch (ValidException e) {
            bindingResult.reject(e.getErrorCode(), e.getErrorMessage());
            return "adminitem/registeritemform";
        }

        return "redirect:/admin/items/{itemId}";
    }

    @AdminUser
    @GetMapping("/{itemId}")
    public String itemEdit(@PathVariable Long itemId, Model model, @MemberId Long memberId) {

        final List<DeliveryDto> deliveryDtos = deliveryService.getDeliveryDtosBy(memberId);
        final ItemRequestDto.Update updateItemDto = itemService.getUpdateItemDtoBy(itemId, memberId);

        model.addAttribute("deliveryDtos", deliveryDtos);
        model.addAttribute("updateItemDto", updateItemDto);


        return "adminitem/updateitemform";
    }

    @PostMapping("/{itemId}")
    public String updateItem(@Valid @ModelAttribute("updateItemDto") ItemRequestDto.Update request, BindingResult bindingResult,
                             @PathVariable Long itemId, @MemberId Long memberId,  RedirectAttributes redirectAttributes, Model model) throws IOException {

        if (request.getOriginalImageNames().get(0).isEmpty() && request.getItemImageFiles().get(0).isEmpty()) {
            bindingResult.reject("NOT_UPLOAD_REP_IMAGE", ErrorCode.NOT_UPLOAD_REP_IMAGE.getMessage());
        }

        if (bindingResult.hasErrors()) {
            final List<DeliveryDto> deliveryDtos = deliveryService.getDeliveryDtosBy(memberId);

            model.addAttribute("deliveryDtos", deliveryDtos);
            model.addAttribute("updateItemDto", request);

            return "adminitem/updateitemform";
        }


        try {
            ItemResponseDto updateItemDto = itemService.updateItem(itemId, memberId, request);
            redirectAttributes.addAttribute("itemId", updateItemDto.getItemId());
        } catch (ValidException e) {
            bindingResult.reject(e.getErrorCode(), e.getErrorMessage());
            return "adminitem/registeritemform";
        }

        return "redirect:/admin/items/{itemId}";
    }
}
