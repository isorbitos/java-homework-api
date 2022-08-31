package com.homework.restapi.model.dto;

import com.homework.restapi.model.JwtUser;
import lombok.Data;

@Data
public class PlainUserDto {
    private Long id;
    private String username;

    public static PlainUserDto from(JwtUser user){
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setUsername(user.getUsername());
        return  plainUserDto;
    }

}
