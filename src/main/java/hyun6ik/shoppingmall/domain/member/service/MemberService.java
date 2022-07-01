package hyun6ik.shoppingmall.domain.member.service;

import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;

public interface MemberService {
    Member register(MemberRegisterDto initMember);
}
