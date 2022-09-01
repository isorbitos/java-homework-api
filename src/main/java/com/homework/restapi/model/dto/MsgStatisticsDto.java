package com.homework.restapi.model.dto;

import com.homework.restapi.model.JwtUser;
import com.homework.restapi.model.Message;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MsgStatisticsDto {
    private String username;
    private Integer msgCount;
    private String firstMsgDate;
    private String lastMsgDate;
    private Integer msgLenghtAverage;
    private String lastMsgText;

    public static MsgStatisticsDto from(JwtUser user){
        MsgStatisticsDto msgStatisticsDto = new MsgStatisticsDto();
        msgStatisticsDto.setUsername(user.getUsername());
        List<Message> messages = user.getMessages().stream().sorted(Comparator.comparing(Message::getMsgCreated)).collect(Collectors.toList());
        msgStatisticsDto.setMsgCount(messages.size());
        if(messages.size() >0){
            msgStatisticsDto.setFirstMsgDate(messages.get(0).getMsgCreated().toString());
            msgStatisticsDto.setLastMsgDate(messages.get(messages.size()-1).getMsgCreated().toString());
            msgStatisticsDto.setMsgLenghtAverage((int) messages.stream().map(msg->msg.getMsgText().chars().count()).mapToLong(number->number).average().orElse(0));
            msgStatisticsDto.setLastMsgText(messages.get(messages.size()-1).getMsgText());

        }
        return msgStatisticsDto;
    }
}
