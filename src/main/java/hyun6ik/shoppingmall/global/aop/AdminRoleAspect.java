package hyun6ik.shoppingmall.global.aop;

import hyun6ik.shoppingmall.domain.login.LoginMemberDetails;
import hyun6ik.shoppingmall.domain.member.constant.MemberRole;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.InvalidRoleAccessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AdminRoleAspect {

    @Before("@annotation(hyun6ik.shoppingmall.global.annotation.AdminUser)")
    public void checkAdminRole() {
        final Member member = ((LoginMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
        log.info("member.getRole = {}", member.getRole());
        if (!MemberRole.ADMIN.equals(member.getRole())) {
            throw new InvalidRoleAccessException(ErrorCode.INVALID_ADMIN_ROLE);
        }
    }
}
