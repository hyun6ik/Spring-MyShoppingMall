package hyun6ik.shoppingmall.infrastructure.member;

import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberStore {

    private final MemberRepository memberRepository;
    public Member store(Member initMember) {
        return memberRepository.save(initMember);
    }
}
