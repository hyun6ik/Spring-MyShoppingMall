package hyun6ik.shoppingmall.domain.member.service;

import hyun6ik.shoppingmall.domain.member.entity.Member;
import hyun6ik.shoppingmall.infrastructure.member.MemberReader;
import hyun6ik.shoppingmall.infrastructure.member.MemberStore;
import hyun6ik.shoppingmall.infrastructure.member.MemberValidator;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberReader memberReader;
    private final MemberStore memberStore;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public MemberRegisterDto.Response register(MemberRegisterDto.Request requestDto) {
        memberValidator.duplicateEmail(requestDto.getEmail());
        final Member initMember = requestDto.toEntity(passwordEncoder.encode(requestDto.getPassword()));
        return MemberRegisterDto.Response.of(memberStore.store(initMember));
    }
}
