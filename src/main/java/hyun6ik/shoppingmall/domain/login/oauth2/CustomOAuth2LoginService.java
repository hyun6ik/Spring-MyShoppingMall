package hyun6ik.shoppingmall.domain.login.oauth2;

import hyun6ik.shoppingmall.domain.login.oauth2.userinfo.OAuth2Attributes;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.global.constraints.AuthConstraints;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.SocialLoginException;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOAuth2LoginService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private static final String ROLE_PREFIX = "ROLE_";

    private final MemberRepository memberRepository;
    private final List<SocialLoginService> socialLoginServices;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        final DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        final OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        final String registrationId = userRequest.getClientRegistration().getRegistrationId();

        final SocialLoginService socialLoginService = routingApiService(registrationId);
        final OAuth2Attributes oAuth2Attributes = socialLoginService.getUserInfo(oAuth2User, registrationId, userRequest);

        final Member member = saveOrUpdate(oAuth2Attributes);

        httpSession.setAttribute(AuthConstraints.MEMBER_ID, member.getId());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(ROLE_PREFIX + member.getRole().name())),
                oAuth2Attributes.getAttributes(),
                oAuth2Attributes.getNameAttributeKey()
        );
    }

    private Member saveOrUpdate(OAuth2Attributes oAuth2Attributes) {
        final Member initMember = memberRepository.findByEmail(oAuth2Attributes.getEmail())
                .map(member -> member.updateName(oAuth2Attributes.getMemberName()))
                .orElse(oAuth2Attributes.toEntity());
        return memberRepository.save(initMember);
    }

    private SocialLoginService routingApiService(String registrationId) {
        return socialLoginServices.stream()
                .filter(socialLoginApiService -> socialLoginApiService.support(registrationId))
                .findFirst()
                .orElseThrow(() -> new SocialLoginException(ErrorCode.INVALID_SOCIAL_LOGIN));
    }
}
