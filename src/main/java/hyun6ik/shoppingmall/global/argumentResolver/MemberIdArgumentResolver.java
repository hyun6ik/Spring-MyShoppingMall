package hyun6ik.shoppingmall.global.argumentResolver;

import hyun6ik.shoppingmall.domain.login.LoginMemberDetails;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import hyun6ik.shoppingmall.global.constraints.AuthConstraints;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(MemberId.class) != null && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final Long socialUserMemberId = (Long) httpSession.getAttribute(AuthConstraints.MEMBER_ID);
        if (socialUserMemberId != null) {
            return socialUserMemberId;
        }

        final LoginMemberDetails loginMemberDetails = (LoginMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (loginMemberDetails.getMember() == null) ? null : loginMemberDetails.getMember().getId();
    }
}
