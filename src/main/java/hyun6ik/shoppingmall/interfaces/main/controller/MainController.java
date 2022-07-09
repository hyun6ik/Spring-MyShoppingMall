package hyun6ik.shoppingmall.interfaces.main.controller;

import hyun6ik.shoppingmall.domain.item.service.ItemService;
import hyun6ik.shoppingmall.interfaces.main.dto.ItemSearchDto;
import hyun6ik.shoppingmall.interfaces.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping("/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.orElse(0), 6); // 페이지 및 한 페이지에 나올 페이지 갯수로 pageable 객체 생성
        final Page<MainItemDto> pageMainItemDto = itemService.getMainItemsBy(itemSearchDto.getSearchQuery(), pageable);

        model.addAttribute("items", pageMainItemDto);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5); // 메인페이지에 노출되는 최대 페이지 갯수

        return "main/mainpage";
    }

}