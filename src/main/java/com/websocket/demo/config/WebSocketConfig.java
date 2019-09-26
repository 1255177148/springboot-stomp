package com.websocket.demo.config;

import com.websocket.demo.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Author hezhan
 * @Date 2019/9/24 11:31
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的端点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加一个/stomp端点，客户端可以通过这个端点进行连接，withSockJS的作用是添加SockJS支持
        registry.addEndpoint("/stomp").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端发送消息的请求前缀
        registry.setApplicationDestinationPrefixes("/webSocket");
        //客户端订阅消息的请求前缀，topic一般用于广播推送，queue用于点对点推送
        registry.enableSimpleBroker("/topic", "/user");
        //服务端通知客户端的前缀，可以不设置，默认为user
        registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        registration.interceptors(createUserInterceptor());
    }

    /**
     * 将自定义的客户端渠道拦截器加入IOC容器中
     * @return
     */
    @Bean
    public UserInterceptor createUserInterceptor(){
        return new UserInterceptor();
    }
}
