package hyun6ik.shoppingmall.infrastructure.login.oauth2;

import hyun6ik.shoppingmall.domain.login.oauth2.SocialLoginService;
import hyun6ik.shoppingmall.domain.login.oauth2.userinfo.NaverUserInfo;
import hyun6ik.shoppingmall.domain.login.oauth2.userinfo.OAuth2Attributes;
import hyun6ik.shoppingmall.domain.member.constant.MemberType;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class NaverLoginService implements SocialLoginService {
    @Override
    public boolean support(String registrationId) {
        return MemberType.NAVER.equals(MemberType.valueOf(registrationId.toUpperCase()));
    }

    @Override
    public OAuth2Attributes getUserInfo(OAuth2User oAuth2User, String registrationId, OAuth2UserRequest userRequest) {
        final String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        return NaverUserInfo.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
    }
}
