package com.homework.restapi.model.dto;

import com.homework.restapi.model.JwtUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JwtUserDto {
    private Long id;
    private String username;
    private List<MessageDto> messagesDto = new ArrayList<>();

    public static JwtUserDto from(JwtUser jwtUser){
        JwtUserDto userDto = new JwtUserDto();
        userDto.setId(jwtUser.getId());
        userDto.setUsername(jwtUser.getUsername());
        userDto.setMessagesDto(jwtUser.getMessages().stream().map(MessageDto::from).collect(Collectors.toList()));
       return userDto;
    };
}
