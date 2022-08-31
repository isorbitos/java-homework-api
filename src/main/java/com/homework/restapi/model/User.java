package com.homework.restapi.model;


import com.homework.restapi.model.dto.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;


    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "user_id")
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message){
        messages.add(message);
    }

    public void removeMessage(Message message){
        messages.remove(message);
    }

    public static User from(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
