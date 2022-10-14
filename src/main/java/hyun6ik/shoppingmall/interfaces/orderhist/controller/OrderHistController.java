package hyun6ik.shoppingmall.interfaces.orderhist.controller;

import hyun6ik.shoppingmall.application.order.OrderFacade;
import hyun6ik.shoppingmall.domain.order.service.OrderService;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderCancelDto;
import hyun6ik.shoppingmall.interfaces.orderhist.dto.OrderHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/orderhist")
@RequiredArgsConstructor
public class OrderHistController {

    private final OrderService orderService;
    private final OrderFacade orderFacade;

    @GetMapping
    public String orderHist(Optional<Integer> page, Model model, @MemberId Long memberId) {
        Pageable pageable = PageRequest.of(page.orElse(0), 6);

        final Page<OrderHistDto> pageOrderHistDtos = orderService.getOrderHistDtosBy(memberId, pageable);

        model.addAttribute("orders", pageOrderHistDtos);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5); // 메인페이지에 노출되는 최대 페이지 갯수
        return "orderhist/orderhist";
    }

    @ResponseBody
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderCancelDto> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderFacade.cancelOrder(orderId));
    }

}
