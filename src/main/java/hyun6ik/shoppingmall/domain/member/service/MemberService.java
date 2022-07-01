package hyun6ik.shoppingmall.domain.member.service;

import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;

public interface MemberService {
    MemberRegisterDto.Response register(MemberRegisterDto.Request requestDto);
}
