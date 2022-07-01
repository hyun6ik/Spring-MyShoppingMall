package hyun6ik.shoppingmall.infrastructure.member;

import hyun6ik.shoppingmall.global.exception.ErrorCode;
import hyun6ik.shoppingmall.global.exception.LoginException;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void duplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new LoginException("ALREADY_REGISTERED_MEMBER", ErrorCode.ALREADY_REGISTERED_MEMBER.getMessage());
        }
    }
}
