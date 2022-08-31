package com.homework.restapi.model.dto;

import com.homework.restapi.model.User;
import lombok.Data;

@Data
public class PlainUserDto {
    private Long id;
    private String username;

    public static PlainUserDto from(User user){
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setUsername(user.getUsername());
        return  plainUserDto;
    }

}
