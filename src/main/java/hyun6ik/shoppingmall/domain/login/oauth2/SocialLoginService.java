package hyun6ik.shoppingmall.domain.login.oauth2;

import hyun6ik.shoppingmall.domain.login.oauth2.userinfo.OAuth2Attributes;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface SocialLoginService {

    boolean support(String registrationId);

    OAuth2Attributes getUserInfo(OAuth2User oAuth2User, String registrationId, OAuth2UserRequest userRequest);
}
