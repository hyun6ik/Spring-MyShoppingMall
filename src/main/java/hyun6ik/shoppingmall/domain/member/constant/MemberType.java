package hyun6ik.shoppingmall.domain.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {

    GENERAL("일반 회원"), GOOGLE("구글 로그인"), KAKAO("카카오 로그인"), NAVER("네이버 로그인");

    private final String description;
}
