package hyun6ik.shoppingmall.domain.login.oauth2.userinfo;

import hyun6ik.shoppingmall.domain.member.constant.MemberType;

import java.util.Map;

public class GoogleUserInfo extends OAuth2Attributes {

    public GoogleUserInfo(Map<String, Object> attributes, String nameAttributeKey, String memberName, String email, MemberType memberType) {
        super(attributes, nameAttributeKey, memberName, email, memberType);
    }
}
