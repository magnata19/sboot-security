package io.github.magnata19.sboot_security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String password = (String) authentication.getCredentials();

        String loginMaster = "master";
        String passwordMaster = "master123";

        if(loginMaster.equals(login) &&  passwordMaster.equals(password)){
            return new UsernamePasswordAuthenticationToken(
                    "usuário master",
                    null,
                    List.of(new SimpleGrantedAuthority("ADMIN")));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
