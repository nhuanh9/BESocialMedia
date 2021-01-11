package com.example.media.controller;

import com.example.media.model.Hello;
import com.example.media.model.UserSocket;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
 
  @MessageMapping("/hello")
  @SendTo("/topic/hii")
  public Hello greeting(UserSocket user) throws Exception {
    return new Hello("Hi, " + user.getName() + "!");
  }
}
