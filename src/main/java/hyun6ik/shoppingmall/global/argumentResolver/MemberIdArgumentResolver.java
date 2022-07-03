package hyun6ik.shoppingmall.global.argumentResolver;


import hyun6ik.shoppingmall.domain.login.LoginMemberDetails;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.global.annotation.MemberId;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(MemberId.class) != null && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final LoginMemberDetails loginMemberDetails = (LoginMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (loginMemberDetails.getMember() == null) ? null : loginMemberDetails.getMember().getId();
    }
}
