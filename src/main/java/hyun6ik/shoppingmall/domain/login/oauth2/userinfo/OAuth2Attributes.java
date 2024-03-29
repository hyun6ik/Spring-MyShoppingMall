package hyun6ik.shoppingmall.domain.login.oauth2.userinfo;

import hyun6ik.shoppingmall.domain.member.constant.MemberRole;
import hyun6ik.shoppingmall.domain.member.constant.MemberType;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2Attributes {

    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String memberName;
    private final String email;
    private final MemberType memberType;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, MemberType memberType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.memberName = memberName;
        this.email = email;
        this.memberType = memberType;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .memberName(memberName)
                .memberType(memberType)
                .role(MemberRole.USER)
                .build();
    }
}
