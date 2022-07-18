package practiceApp.userauth.authentication;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter{
    private String principalRequestHeader;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/**");
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> username = Optional.ofNullable(request.getHeader("username"));
        if (username.isEmpty()) {
            throw new BadCredentialsException("User is invalid");
        }
        Optional<String> password = Optional.ofNullable(request.getHeader("password"));
        if (password.isEmpty()) {
            throw new BadCredentialsException("User is invalid");
        }
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username.get(), password.get()));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
