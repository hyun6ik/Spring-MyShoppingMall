package hyun6ik.shoppingmall.interfaces.login.dto;

import hyun6ik.shoppingmall.domain.member.constant.MemberRole;
import hyun6ik.shoppingmall.domain.member.constant.MemberType;
import hyun6ik.shoppingmall.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class MemberRegisterDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private String name;

        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Size(min = 8, max = 16)
        private String password;

        private String password2;

        @Builder
        public Request(String name, String email, String password, String password2) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.password2 = password2;
        }

        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .memberName(name)
                    .email(email)
                    .password(encodedPassword)
                    .role(MemberRole.USER)
                    .memberType(MemberType.GENERAL)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {

        private String name;
        private String email;

        @Builder
        public Response(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public static Response of(Member member) {
            return new Response(member.getMemberName(), member.getEmail());
        }
    }



}
