package hyun6ik.shoppingmall.infrastructure.member.repository;

import hyun6ik.shoppingmall.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
