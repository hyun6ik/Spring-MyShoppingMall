package hyun6ik.shoppingmall.common.factory;

import hyun6ik.shoppingmall.domain.member.constant.MemberRole;
import hyun6ik.shoppingmall.domain.member.entity.Member;

public class MemberFactory {

    public static Member member() {
        return Member.builder()
                .memberName("회원1")
                .email("aa@naver.com")
                .password("DASDASDASDASDASDASDASDASD")
                .role(MemberRole.ADMIN)
                .build();
    }
}
