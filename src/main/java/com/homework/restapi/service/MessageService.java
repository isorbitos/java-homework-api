package com.homework.restapi.service;

import com.homework.restapi.model.Message;
import com.homework.restapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getMessages(){
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public  Message getMessage(Long id){
        return messageRepository.findById(id).orElseThrow(()-> new RuntimeException("Could not find msg"));
    }

    public Message deleteMessage(Long id){
        Message message = getMessage(id);
        messageRepository.delete(message);
        return  message;
    }

    @Transactional
    public  Message editMessage(Long id, Message message){
        Message messageToEdit = getMessage(id);
        messageToEdit.setMsgText(message.getMsgText());
        return messageToEdit;
    }

}
