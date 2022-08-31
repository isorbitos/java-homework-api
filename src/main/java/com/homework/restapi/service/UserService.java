package com.homework.restapi.service;

import com.homework.restapi.model.Message;
import com.homework.restapi.model.User;
import com.homework.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MessageService messageService;


    @Autowired
    public UserService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("Could not find user"));
    }

    public User deleteUser(Long id){
        User user = getUser(id);
        userRepository.delete(user);
        return user;
    }

    @Transactional
    public User editUser(Long id, User user){
        User userToEdit = getUser(id);
        userToEdit.setUsername(user.getUsername());
        userToEdit.setPassword(user.getPassword());
        return userToEdit;
    }

    @Transactional
    public User addMessageToUser(Long userId, Long messageId){
        User user = getUser(userId);
        Message message = messageService.getMessage(messageId);
        if(Objects.nonNull(message.getUser())){
            throw new RuntimeException("Message is assigned to user");
        }
        user.addMessage(message);
        message.setUser(user);
        return user;
    }

    @Transactional
    public User removeMessageFromUser(Long userId, Long messageId){
        User user = getUser(userId);
        Message message = messageService.getMessage(messageId);
        user.removeMessage(message);
        return user;
    }

    @Transactional
    public void initRolesAndUsers(){
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("asdfgh");
        userRepository.save(adminUser);

        User user = new User();
        user.setUsername("user");
        user.setPassword("asdfgh");
        userRepository.save(user);
    }


}
