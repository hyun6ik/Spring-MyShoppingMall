package hyun6ik.shoppingmall.interfaces.login.controller;

import hyun6ik.shoppingmall.domain.member.service.MemberService;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.LoginException;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberRegisterDto", new MemberRegisterDto());
        return "login/registerform";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("memberRegisterDto") MemberRegisterDto.Request requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/registerform";
        }

        if (!StringUtils.equals(requestDto.getPassword(), requestDto.getPassword2())) {
            bindingResult.reject("mismatchedPassword", ErrorCode.MISMATCHED_PASSWORD.getMessage());
            return "login/registerform";
        }

        try {
            memberService.register(requestDto);
        } catch (LoginException e) {
            bindingResult.reject(e.getErrorCode(), e.getErrorMessage());
            return "login/registerform";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("message", ErrorCode.LOGIN_ERROR.getMessage());

        return "login/loginform";
    }
}
