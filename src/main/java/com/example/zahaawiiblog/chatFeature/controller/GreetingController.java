package com.example.zahaawiiblog.chatFeature.controller;

import com.example.zahaawiiblog.chatFeature.entity.Greeting;
import com.example.zahaawiiblog.chatFeature.entity.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Username: " + message.getName() + " " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}