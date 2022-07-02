package hyun6ik.shoppingmall.domain.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {

    GENERAL("일반 회원");

    private final String description;
}
