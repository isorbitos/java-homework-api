package com.homework.restapi;

import com.homework.restapi.model.JwtUser;
import com.homework.restapi.model.Role;
import com.homework.restapi.service.JwtUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitJwtUsers implements CommandLineRunner {

    private final JwtUserService jwtUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(jwtUserService.findJwtUserByEmail("admin@test.lt").isEmpty()){
            JwtUser user =jwtUserService.save(JwtUser.builder()
                    .username("admin")
                    .email("admin@test.lt")
                    .password(passwordEncoder.encode("123456"))
                    .role(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                    .build()
            );
            user.setEnabled(true);
            jwtUserService.save(user);
        }

        if(jwtUserService.findJwtUserByEmail("user@test.lt").isEmpty()){
            JwtUser user =jwtUserService.save(JwtUser.builder()
                    .username("user")
                    .email("user@test.lt")
                    .password(passwordEncoder.encode("123456"))
                    .role(Set.of(Role.ROLE_ADMIN))
                    .build()
            );
            user.setEnabled(true);
            jwtUserService.save(user);
        }

    }
}
