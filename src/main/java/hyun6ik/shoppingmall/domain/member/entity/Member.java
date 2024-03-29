package hyun6ik.shoppingmall.domain.member.entity;

import hyun6ik.shoppingmall.domain.base.BaseTimeEntity;
import hyun6ik.shoppingmall.domain.member.constant.MemberRole;
import hyun6ik.shoppingmall.domain.member.constant.MemberType;
import hyun6ik.shoppingmall.global.utils.TokenGenerator;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity implements Serializable {

    private static final String PREFIX_MEMBER = "member_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String memberName;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberType type;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberRole role;

    private String memberToken;


    @Builder
    public Member(String memberName, String email, String password, MemberRole role, MemberType memberType) {
        this.email = email;
        this.memberName = memberName;
        this.type = memberType;
        this.password = password;
        this.role = role;
        this.memberToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_MEMBER);
    }

    public Member updateName(String memberName) {
        this.memberName = memberName;
        return this;
    }
}
