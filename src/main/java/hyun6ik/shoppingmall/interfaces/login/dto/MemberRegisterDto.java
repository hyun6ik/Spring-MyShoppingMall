package hyun6ik.shoppingmall.interfaces.login.dto;

import hyun6ik.shoppingmall.domain.member.constant.MemberRole;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class MemberRegisterDto {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    private String password2;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .password(encodedPassword)
                .role(MemberRole.ADMIN)
                .build();
    }
}
