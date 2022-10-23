package hyun6ik.shoppingmall.domain.login.oauth2;

import hyun6ik.shoppingmall.domain.member.entity.Member;
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

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private static final String ROLE_PREFIX = "ROLE_";

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        final DefaultOAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        final OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        final String registrationId = userRequest.getClientRegistration().getRegistrationId();
        final String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        final OAuth2Attributes oAuth2Attributes = OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        final Member member = saveOrUpdate(oAuth2Attributes);

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
}
