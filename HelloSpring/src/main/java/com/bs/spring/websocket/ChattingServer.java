package com.bs.spring.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//websocket 서버로 활용되는 pojo
//TextWebSocketHandler 클래스를 상속 받아서 구현 -> 써야하는 메소드들이다 구현이 되어있따!

@Slf4j
public class ChattingServer extends TextWebSocketHandler{
	
//	private Map<String,Map<WebSocketSession,Message>> clients= new HashMap();
	private Map<String,WebSocketSession> clients= new HashMap();
//				<아이디, 세션>

	private ObjectMapper mapper;
	
	@Autowired
	public void setMapper(ObjectMapper mapper) {
		this.mapper=mapper;
	
	}
	
	//alt shift s v -> abstractweb... 아래의 위에서 세개	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		log.debug("클라이언트 접속");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		//super.handleTextMessage(session, message);
		
		//클라이언트가 보낸 메세지 확인하기
		//클라이언트가 보낸 메세지는 payload에 저장된다.
		log.debug("{}",message.getPayload());
		
		ObjectMapper mapper=new ObjectMapper();
		SendMessage msg=mapper.readValue(message.getPayload(), SendMessage.class); 
		//json방식의 데이터를 readValue가 parse. key값을 기준으로 Message 클래스의 필드랑 매칭해서.
		log.debug("{}",msg);
		
		switch(msg.getType()) {
		case "open" : addClient(session,msg);break; //client 정보에 추가
		case "msg" : sendMessage(msg);break; //메세지를 클라이언트에게 전달
		case "system" : sendAdminMessage();break; //시스템 정보를 클라이언트에게 전달
		
			
		}
		
	}
	
	private void addClient(WebSocketSession session,SendMessage msg) throws JsonProcessingException, IOException {
		session.getAttributes().put("info", msg);
		clients.put(msg.getSender(), session);
		SendMessage adminmsg=new SendMessage("system","","",msg.getSender()+"가 접속했습니다.","");
		ObjectMapper mapper=new ObjectMapper();
		
		for(String id:clients.keySet()) {
			WebSocketSession client=clients.get(id);			
			client.sendMessage(new TextMessage(mapper.writeValueAsString(adminmsg)));
		}
		
	}
	
	private void sendMessage(SendMessage msg) throws IOException {
		for(String id:clients.keySet()) {
			WebSocketSession client=clients.get(id);
			client.sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
		}
		
		
	}
	
	private void sendAdminMessage() {
		
	}



	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}

	
	
}
