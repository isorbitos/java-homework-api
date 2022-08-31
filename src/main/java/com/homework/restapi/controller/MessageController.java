package com.homework.restapi.controller;

import com.homework.restapi.model.Message;
import com.homework.restapi.model.dto.MessageDto;
import com.homework.restapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private  final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> addMessage(@RequestBody final MessageDto messageDto){
        Message message = messageService.addMessage(Message.from(messageDto));
        return new ResponseEntity<>(MessageDto.from(message), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages(){
        List<Message> messages = messageService.getMessages();
        List<MessageDto> messageDtos = messages.stream().map(MessageDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }


}
