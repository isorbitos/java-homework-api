//package com.homework.restapi.model.dto;
//
//import com.homework.restapi.model.User;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//public class UserDto {
//    private Long id;
//    private String username;
//    private String password;
//    private List<MessageDto> messagesDto = new ArrayList<>();
//
//    public static UserDto from(User user){
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUsername(user.getUsername());
//        userDto.setPassword(user.getPassword());
//        userDto.setMessagesDto(user.getMessages().stream().map(MessageDto::from).collect(Collectors.toList()));
//        return userDto;
//    }
//}

