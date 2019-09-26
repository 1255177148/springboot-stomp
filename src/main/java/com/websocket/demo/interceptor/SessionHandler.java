package com.websocket.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author hezhan
 * @Date 2019/9/24 17:15
 */
@Component
public class SessionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public SessionHandler(){

    }

    public void register(WebSocketSession session){
        sessionMap.put(session.getPrincipal().getName(), session);
    }

    public void remove(WebSocketSession session){
        sessionMap.remove(session.getPrincipal().getName());
    }

    /**
     * 查看是否在线
     * @param openid
     * @return
     */
    public boolean isOnline(String openid){
        return sessionMap.get(openid) != null;
    }
}
