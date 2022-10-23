package hyun6ik.shoppingmall.domain.login.oauth2.userinfo;

import hyun6ik.shoppingmall.domain.member.constant.MemberType;
import lombok.Builder;

import java.util.Map;

public class GoogleUserInfo extends OAuth2Attributes {

    @Builder
    public GoogleUserInfo(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, MemberType memberType) {
        super(attributes, nameAttributeKey, memberName, email, memberType);
    }

    public static GoogleUserInfo of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return GoogleUserInfo.builder()
                .memberName((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .memberType(MemberType.valueOf(registrationId.toUpperCase()))
                .build();
    }
}
