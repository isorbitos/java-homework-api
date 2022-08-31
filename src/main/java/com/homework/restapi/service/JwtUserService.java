package com.homework.restapi.service;

import com.homework.restapi.model.JwtUser;
import com.homework.restapi.model.Message;
import com.homework.restapi.repository.JwtUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class JwtUserService {
    private final JwtUserRepository jwtUserRepository;
    private final MessageService messageService;

    public JwtUser save(JwtUser user) {
        return jwtUserRepository.save(user);
    }

    public List<JwtUser> getUsers(){
        return StreamSupport.stream(jwtUserRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public JwtUser getUserById(Long id){
        return jwtUserRepository.findById(id).orElseThrow(()-> new RuntimeException("Could not find user"));
    }

    public JwtUser deleteUser(Long id){
        JwtUser user = getUserById(id);
        jwtUserRepository.delete(user);
        return user;
    }

    public Optional<JwtUser> findJwtUserByEmail(String email) {
        return jwtUserRepository.findJwtUserByEmail(email);
    }

    public JwtUser getJwtUserByEmail(String email) {
        return jwtUserRepository.findJwtUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found by email!"));
    }

    public JwtUser getJwtUserByUsername(String username) {
        return jwtUserRepository.findJwtUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found by username!"));
    }

    @Transactional
    public JwtUser addMessageToUser(Long userId, Long messageId){
        JwtUser user = getUserById(userId);
        Message message = messageService.getMessage(messageId);
        if(Objects.nonNull(message.getUser())){
            throw new RuntimeException("Message is assigned to user");
        }
        user.addMessage(message);
        message.setUser(user);
        return user;
    }

}
