package com.homework.restapi.model.dto;

import com.homework.restapi.model.Message;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
public class MessageDto {
    private Long id;
    private String msgText;
    private Timestamp msgCreated;
    private PlainUserDto plainUserDto;

    public static MessageDto from(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMsgText(message.getMsgText());
        messageDto.setMsgCreated(message.getMsgCreated());
        if(Objects.nonNull(message.getUser())){
            messageDto.setPlainUserDto(PlainUserDto.from(message.getUser()));
        }
        return messageDto;
    }

}
