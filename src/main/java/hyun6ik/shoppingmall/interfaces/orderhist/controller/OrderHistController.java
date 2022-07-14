package hyun6ik.shoppingmall.interfaces.orderhist.controller;

import hyun6ik.shoppingmall.domain.order.service.OrderService;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/orderhist")
@RequiredArgsConstructor
public class OrderHistController {

    private final OrderService orderService;

    @GetMapping
    public String orderHist(Optional<Integer> page, Model model, @MemberId Long memberId) {
        Pageable pageable = PageRequest.of(page.orElse(0), 6);

        final Page<OrderHistDto> pageOrderHistDtos = orderService.getOrderHistDtosBy(memberId, pageable);

        model.addAttribute("orders", pageOrderHistDtos);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5); // 메인페이지에 노출되는 최대 페이지 갯수
        return "orderhist/orderhist";
    }

}
