package com.study.oauth2.config;

import com.study.oauth2.domain.Role;
import com.study.oauth2.domain.User;
import com.study.oauth2.service.impl.GenericServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials()
                .toString();

        GenericServiceImpl userService = new GenericServiceImpl();

        if(userService.checkUser(username,password)){
            User user = userService.findByUsername(username);



            if(user == null) {
                String[] stringTokenized = username.split("\\.");

                user = new User();

                user.setUsername(username);
                user.setFirstName(stringTokenized[0]);
                user.setLastName(stringTokenized[1]);

                Set<Role> roles = new HashSet<>();

                roles.add(roleService.getRole("Talento"));
                user.setRoles(roles);

                userService.saveUser(user);
            }

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

          /*  List<GrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                System.out.println("Adicionada a regra: "+role.getRoleName());
            });*/

            return new UsernamePasswordAuthenticationToken(username,password,authorities);

        }else
            throw new UsernameNotFoundException(String.format("Erro na autenticacao"));

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
