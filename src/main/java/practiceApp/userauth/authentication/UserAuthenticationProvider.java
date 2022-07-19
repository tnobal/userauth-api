package practiceApp.userauth.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import practiceApp.userauth.users.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Component
@EnableWebSecurity
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository usersRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication);
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (usersRepo.existsById(username) && usersRepo.findById(username).get().getPassword().equals(password)) {
            System.out.println("good");
            Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        System.out.println("bad");
        throw new BadCredentialsException("User is invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}