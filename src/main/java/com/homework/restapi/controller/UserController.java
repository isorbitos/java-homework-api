package com.homework.restapi.controller;

import com.homework.restapi.model.User;
import com.homework.restapi.model.dto.UserDto;
import com.homework.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto>addUser(@RequestBody final UserDto userDto){
        User user = userService.addUser(User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getUsers();
        List<UserDto> usersDto = users.stream().map(UserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable final Long id){
        User user = userService.getUser(id);
        return  new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable final Long id){
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable final Long id, @RequestBody final UserDto userDto){
        User user = userService.editUser(id, User.from(userDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PostMapping(value = "{userId}/items/{messageId}/add")
    public ResponseEntity<UserDto> addMessageToUser(@PathVariable final Long userId, @PathVariable final long messageId){
        User user = userService.addMessageToUser(userId, messageId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PostMapping(value = "{userId}/items/{messageId}/remove")
    public ResponseEntity<UserDto> removeMessageFromUser(@PathVariable final Long userId, @PathVariable final Long messageId){
        User user = userService.removeMessageFromUser(userId, messageId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

}
