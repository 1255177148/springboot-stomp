package com.websocket.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

/**
 * @Author hezhan
 * @Date 2019/9/24 17:13
 */
public class CustomSubProtocolWebSocketHandler extends SubProtocolWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionHandler sessionHandler;

    public CustomSubProtocolWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        logger.info("新的webSocket连接建立");
        sessionHandler.register(session);
        super.afterConnectionEstablished(session);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception{
        logger.info("名为" + session.getPrincipal().getName() + "的webSocket连接被关闭");
        sessionHandler.remove(session);
        super.afterConnectionClosed(session, closeStatus);
    }
}
