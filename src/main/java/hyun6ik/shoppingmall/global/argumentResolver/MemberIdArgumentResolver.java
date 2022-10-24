package hyun6ik.shoppingmall.global.argumentResolver;

import hyun6ik.shoppingmall.domain.login.LoginMemberDetails;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import hyun6ik.shoppingmall.global.constraints.AuthConstraints;
import hyun6ik.shoppingmall.global.exception.AuthenticationException;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@Slf4j
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

        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (isAnonymousUser(principal)) {
            throw new AuthenticationException(ErrorCode.NOT_LOGIN_MEMBER);
        }

        return ((LoginMemberDetails) principal).getMember().getId();
    }

    private boolean isAnonymousUser(Object principal) {
        return principal.equals("anonymousUser");
    }
}
