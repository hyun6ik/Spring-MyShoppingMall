package hyun6ik.shoppingmall.domain.member.service;

import hyun6ik.shoppingmall.global.exception.LoginException;
import hyun6ik.shoppingmall.infrastructure.member.MemberReader;
import hyun6ik.shoppingmall.infrastructure.member.MemberStore;
import hyun6ik.shoppingmall.infrastructure.member.MemberValidator;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberStore memberStore;

    @Autowired
    MemberValidator memberValidator;

    @Autowired
    MemberReader memberReader;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
    }


    @Test
    @DisplayName("회원가입 - 성공")
    void register() {
        //given
        final MemberRegisterDto.Request requestDto = MemberRegisterDto.Request.builder()
                .email("member@register_service.com")
                .name("register_success")
                .password("12345678")
                .password2("12345678")
                .build();
        //when
        final MemberRegisterDto.Response response = memberService.register(requestDto);
        //then
        assertThat(response.getEmail()).isEqualTo(requestDto.getEmail());
        assertThat(response.getName()).isEqualTo(requestDto.getName());
    }

    @Test
    @DisplayName("회원가입 실패 - 이메일 중복")
    void register_fail_duplicate() {
        //given
        final MemberRegisterDto.Request oldRequestDto = MemberRegisterDto.Request.builder()
                .email("member@register_service.com")
                .name("fail")
                .password("12345678")
                .password2("12345678")
                .build();

        final MemberRegisterDto.Request newRequestDto = MemberRegisterDto.Request.builder()
                .email("member@register_service.com")
                .name("aaa")
                .password("12345678")
                .password2("12345678")
                .build();
        memberService.register(oldRequestDto);
        //when && then
        assertThatThrownBy(() -> memberService.register(newRequestDto))
                .isInstanceOf(LoginException.class);
    }
}