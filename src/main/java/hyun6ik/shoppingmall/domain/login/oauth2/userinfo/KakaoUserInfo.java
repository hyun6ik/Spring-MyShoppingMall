package hyun6ik.shoppingmall.domain.login.oauth2.userinfo;

import hyun6ik.shoppingmall.domain.member.constant.MemberType;

import java.util.Map;

public class KakaoUserInfo extends OAuth2Attributes{

    public KakaoUserInfo(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, MemberType memberType) {
        super(attributes, nameAttributeKey, memberName, email, memberType);
    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> attributesProfile = (Map<String, Object>) attributesAccount.get("profile");

        return OAuth2Attributes.builder()
                .memberName((String) attributesProfile.get("nickname"))
                .email((String) attributesAccount.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .memberType(MemberType.valueOf(registrationId.toUpperCase()))
                .build();
    }
}
