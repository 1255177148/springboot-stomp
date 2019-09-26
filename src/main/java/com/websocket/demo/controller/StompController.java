package com.websocket.demo.controller;

import com.websocket.demo.entity.Message;
import com.websocket.demo.interceptor.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author hezhan
 * @Date 2019/9/24 17:28
 */
@RestController
public class StompController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    SessionHandler sessionHandler;

    @MessageMapping("/message")
    public void subscription(Message message, Principal principal) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue", new Message(message.getMessage(), message.getDatetime(), message.getFrom(), message.getTo()));
        System.out.println(principal.getName() + "发送了一条消息给：" + message.getTo());
    }
}
