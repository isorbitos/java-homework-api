package com.homework.restapi;

import com.homework.restapi.model.JwtUser;
import com.homework.restapi.model.Message;
import com.homework.restapi.model.Role;
import com.homework.restapi.service.JwtUserService;
import com.homework.restapi.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitJwtUsers implements CommandLineRunner {

    private final JwtUserService jwtUserService;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;



    @Override
    public void run(String... args) throws Exception {

        Message msg1 = messageService.addMessage(Message.builder()
                .msgText("text111")
                .msgCreated(new Timestamp(System.currentTimeMillis()))
                .build());

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
            jwtUserService.addMessageToUser(user.getId(), msg1.getId());
        }
        Message msg2 = messageService.addMessage(Message.builder()
                .msgText("text111")
                .msgCreated(new Timestamp(System.currentTimeMillis()))
                .build());
        Message msg3 = messageService.addMessage(Message.builder()
                .msgText("text111")
                .msgCreated(new Timestamp(System.currentTimeMillis()))
                .build());
        Message msg4 = messageService.addMessage(Message.builder()
                .msgText("text111")
                .msgCreated(new Timestamp(System.currentTimeMillis()))
                .build());

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
            jwtUserService.addMessageToUser(user.getId(), msg2.getId());
            jwtUserService.addMessageToUser(user.getId(), msg3.getId());
            jwtUserService.addMessageToUser(user.getId(), msg4.getId());
        }

    }
}
