package hyun6ik.shoppingmall.common.factory;

import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;

public class LoginFactory {

    public static MemberRegisterDto.Request getMockingMemberRegisterRequestDto() {
        return MemberRegisterDto.Request.builder()
                .name("이름")
                .email("aaa@naver.com")
                .password("12345678")
                .password2("12345678")
                .build();
    }

    public static MemberRegisterDto.Response getMockingMemberRegisterResponseDto() {
        return MemberRegisterDto.Response
                .builder()
                .name("이름")
                .email("aaa@naver.com")
                .build();
    }
}
