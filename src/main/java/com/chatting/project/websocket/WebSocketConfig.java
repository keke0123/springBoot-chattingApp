package com.chatting.project.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

// configuration 은 설정 관련
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// 메모리 기반 메세지 브로커가 해당 api 를 구독하고 있는 클라이언트에게 메세지를 전달한다.
		config.enableSimpleBroker("/topic");
		// 서버에서 클라이언트로부터의 메세지를 받을 api의 prefix 를 설정한다. 
		config.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 클라이언트에서 websocket 을 연결할 api 를 설정한다.
		// parameter 로 넘겨받는 'stompendpointregistry' 의 메소드인
		// 'addendpoint' 메소드를 통해서 여러 가지 end point 를 설정할 수 있다.
		registry.addEndpoint("/handler").setAllowedOrigins("*").withSockJS();
	}
	
	
}
