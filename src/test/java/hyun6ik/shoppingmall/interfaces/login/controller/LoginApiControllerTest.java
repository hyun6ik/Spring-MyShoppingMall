package hyun6ik.shoppingmall.interfaces.login.controller;

import hyun6ik.shoppingmall.common.factory.LoginFactory;
import hyun6ik.shoppingmall.interfaces.ControllerTest;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static hyun6ik.shoppingmall.global.utils.JsonUtils.toJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class LoginApiControllerTest extends ControllerTest {

    @DisplayName("[회원 가입 테스트]")
    @Nested
    class LoginUnitTest {
        @Test
        @DisplayName("회원가입에 성공한다.")
        void register() throws Exception {
            //given
            final MemberRegisterDto.Request request = LoginFactory.getMockingMemberRegisterRequestDto();
            final MemberRegisterDto.Response response = LoginFactory.getMockingMemberRegisterResponseDto();

            given(memberService.register(any(MemberRegisterDto.Request.class)))
                    .willReturn(response);

            //when
            final ResultActions result = mockMvc.perform(
                    post("/api/v1/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(toJson(request))
            );
            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data").exists())
                    .andExpect(jsonPath("$.data.name", is(response.getName())))
                    .andExpect(jsonPath("$.data.email", is(response.getEmail())))
                    .andExpect(jsonPath("$.error").doesNotExist());
        }

        @Test
        @DisplayName("email 파라미터가 누락되면 API 호출을 실패한다.")
        void register_fail1() throws Exception {
            //given
            final MemberRegisterDto.Request request = MemberRegisterDto.Request
                    .builder()
                    .name("이름")
                    .password("12345678")
                    .password2("12345678")
                    .build();
            //when
            final ResultActions result = mockMvc.perform(
                    post("/api/v1/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(toJson(request))
            );
            //then
            result.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data").doesNotExist())
                    .andExpect(rslt -> assertThat(rslt.getResolvedException().getClass()).isAssignableFrom(MethodArgumentNotValidException.class));
        }

        @Test
        @DisplayName("name 파라미터가 누락되면 API 호출을 실패한다.")
        void register_fail2() throws Exception {
            //given
            final MemberRegisterDto.Request request = MemberRegisterDto.Request
                    .builder()
                    .email("aaa@naver.com")
                    .password("12345678")
                    .password2("12345678")
                    .build();
            //when
            final ResultActions result = mockMvc.perform(
                    post("/api/v1/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(toJson(request))
            );
            //then
            result.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data").doesNotExist())
                    .andExpect(rslt -> assertThat(rslt.getResolvedException())
                            .isInstanceOf(MethodArgumentNotValidException.class));
        }

    }



}
