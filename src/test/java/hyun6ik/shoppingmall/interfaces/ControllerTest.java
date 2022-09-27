package hyun6ik.shoppingmall.interfaces;

import hyun6ik.shoppingmall.domain.login.service.LoginService;
import hyun6ik.shoppingmall.domain.member.service.MemberService;
import hyun6ik.shoppingmall.global.config.security.CustomAuthenticationEntryPoint;
import hyun6ik.shoppingmall.global.config.security.CustomAuthenticationFailureHandler;
import hyun6ik.shoppingmall.infrastructure.member.MemberValidator;
import hyun6ik.shoppingmall.infrastructure.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public abstract class ControllerTest {

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected MemberValidator memberValidator;

    @MockBean
    protected LoginService loginService;

    @MockBean
    protected CustomAuthenticationFailureHandler authenticationFailureHandler;

    @MockBean
    protected CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    protected MockMvc mockMvc;
}
