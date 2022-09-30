package hyun6ik.shoppingmall.interfaces.login.controller;

import hyun6ik.shoppingmall.domain.member.service.MemberService;
import hyun6ik.shoppingmall.global.response.ApiResult;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginApiController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ApiResult<MemberRegisterDto.Response> registerMember(@RequestBody @Valid MemberRegisterDto.Request request) {
        return ApiResult.succeed(memberService.register(request));
    }
}
