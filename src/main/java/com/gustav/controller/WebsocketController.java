package com.gustav.controller;

import com.gustav.model.Greeting;
import com.gustav.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gustav on 2017/8/7.
 */
@Controller
public class WebsocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @SendTo("/topic/greetings")
    public Greeting post(){
        HelloMessage message = new HelloMessage("gustav");
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
