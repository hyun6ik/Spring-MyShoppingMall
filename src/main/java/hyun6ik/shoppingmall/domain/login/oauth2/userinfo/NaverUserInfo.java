package hyun6ik.shoppingmall.domain.login.oauth2.userinfo;

import hyun6ik.shoppingmall.domain.member.constant.MemberType;

import java.util.Map;

public class NaverUserInfo extends OAuth2Attributes{
    public NaverUserInfo(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, MemberType memberType) {
        super(attributes, nameAttributeKey, memberName, email, memberType);
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> attributesProfile = (Map<String, Object>) attributes.get("response");

        return OAuth2Attributes.builder()
                .memberName((String) attributesProfile.get("name"))
                .email((String) attributesProfile.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .memberType(MemberType.valueOf(registrationId.toUpperCase()))
                .build();
    }
}
