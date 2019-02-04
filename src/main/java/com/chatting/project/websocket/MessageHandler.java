package com.chatting.project.websocket;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class MessageHandler {
	// /hello 라는 api 로 메세지를 보내면 broadcasting() 메소드가 호출된다.
	// 클라이언트로부터 오는 메세지는 broadcasting() 메소드의 파라미터와 binding 되어 있다.
	// return value 는 @sendTo 어노테이션에 mapping 되어있는 api 를 구독하고 있는 클라이언트들에게 브로드캐스팅 된다.
	@MessageMapping("/keke") // /app/all 이면 이쪽으로 맵핑 되는듯 하다 / 여기에 all 대신 {id} 값을 넣어도 될듯
	@SendTo("/topic/keke") // /topic 은 client 에 값을 넘겨줄때
	public Greeting greeting(HelloMessage message) throws Exception {
		System.out.println(message);
		Thread.sleep(1000); // simulated delay 지우면 된다.
		return new Greeting("hello, "+HtmlUtils.htmlEscape(message.getName()) +"!");
	}
    @MessageMapping("/all")
    @SendTo("/topic/all")
    public Map<String, String> post(@Payload Map<String, String> message) {
        message.put("timestamp", Long.toString(System.currentTimeMillis()));
        return message;
    }
	
}
