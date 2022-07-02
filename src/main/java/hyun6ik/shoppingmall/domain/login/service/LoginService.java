package hyun6ik.shoppingmall.domain.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService extends UserDetailsService {

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
