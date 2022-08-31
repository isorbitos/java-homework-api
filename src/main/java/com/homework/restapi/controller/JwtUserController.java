package com.homework.restapi.controller;

import com.homework.restapi.model.JwtUser;
import com.homework.restapi.model.dto.JwtUserDto;
import com.homework.restapi.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JwtUserController {


    private final JwtUserService userServicese;

    @Autowired
    public JwtUserController(JwtUserService userServicese) {
        this.userServicese = userServicese;
    }

    @GetMapping("/users")
    public ResponseEntity<List<JwtUserDto>> getUsers(){
        List<JwtUser> users = userServicese.getUsers();
        List<JwtUserDto> userDtos = users.stream().map(JwtUserDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping(value = "{userId}/messages/{messageId}/add")
    public ResponseEntity<JwtUserDto> addMessageToUser(@PathVariable final Long userId, @PathVariable final long messageId){
        JwtUser user = userServicese.addMessageToUser(userId,messageId);
        return new ResponseEntity<>(JwtUserDto.from(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteuser/{userId}")
    public ResponseEntity<JwtUserDto>deleteUser(@PathVariable final Long userId){
        JwtUser user = userServicese.deleteUser(userId);
        return new ResponseEntity<>(JwtUserDto.from(user), HttpStatus.OK);
    }



}
