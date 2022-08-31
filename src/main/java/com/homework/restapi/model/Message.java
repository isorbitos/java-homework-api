package com.homework.restapi.model;

import com.homework.restapi.model.dto.MessageDto;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msgText;
    private Timestamp msgCreated;

    @ManyToOne
    private User user;

    public static Message from(MessageDto messageDto){
        Message message = new Message();
        message.setMsgText(messageDto.getMsgText());
        message.setMsgCreated(new Timestamp(System.currentTimeMillis()));
        return message;
    }

}
