package com.example.media.controller;

import com.example.media.model.Hello;
import com.example.media.model.UserSocket;
import com.example.media.model.entity.RomChatEntity;
import com.example.media.service.IRomChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
  private String userNameFriend;
  private Long idRomChat;
  @Autowired
  SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  IRomChatService iRomChatService;
 
//  @MessageMapping("/hello")
//  @SendTo("/topic/hii")
//  public Hello greeting1(UserSocket user) throws Exception {
//    return new Hello("Hi, " + user.getName() + "!");
//  }

  @MessageMapping("/chat.newUser")
  public void greeting(@Payload UserSocket user) {
    simpMessagingTemplate.convertAndSend("/topic/public/"+ this.idRomChat, user.getName() +" : " + user.getMessage());

    System.out.println("-------------------------------------------");
    System.out.println("/topic/public/"+ user.getName() + "/" + this.userNameFriend);
    System.out.println("-------------------------------------------");
  }
  @MessageMapping("/chatVsUser")
  public UserSocket chatVsUser(@Payload UserSocket user, SimpMessageHeaderAccessor headerAccessor) {
    this.userNameFriend = user.getUserNameFriend();
    RomChatEntity romChatEntity = iRomChatService.findRomChat(user.getName(),user.getUserNameFriend());
    this.idRomChat = romChatEntity.getId();
    return user;
  }

}
