package hyun6ik.shoppingmall.interfaces.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun6ik.shoppingmall.domain.member.service.MemberService;
import hyun6ik.shoppingmall.interfaces.login.dto.MemberRegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    MemberService memberService;

    @MockBean
    BindingResult bindingResult;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void register_success() throws Exception {
        //given
        final MemberRegisterDto.Request requestDto = MemberRegisterDto.Request.builder()
                .email("member@register.com")
                .name("register_success")
                .password("12345678")
                .password2("12345678")
                .build();

        //when
        final ResultActions result = mockMvc.perform(
                post("/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        result.andExpect(status().isOk());
    }
}