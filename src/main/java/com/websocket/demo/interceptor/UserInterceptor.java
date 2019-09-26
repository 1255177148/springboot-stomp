package com.websocket.demo.interceptor;

import com.websocket.demo.entity.FastPrincipal;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.LinkedList;
import java.util.Map;

/**
 * @Author hezhan
 * @Date 2019/9/25 15:25
 * 客户端渠道拦截适配器
 */
public class UserInterceptor implements ChannelInterceptor {

    /**
     * 获取包含在stomp中的用户信息
     */
    public Message<?> preSend(Message<?> message, MessageChannel channel){
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())){
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map){
                Object name = ((Map)raw).get("name");
                if (name instanceof LinkedList){
                    //设置当前访问器的认证用户
                    accessor.setUser(new FastPrincipal(((LinkedList)name).get(0).toString()));
                }
            }
        }
        return message;
    }
}
