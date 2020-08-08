package com.linker.link.room;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linker.link.chat.ChatMessage;

public class RoomDTO{
	private String id;
	private String pw;
	private String name;
	private String host;
	private int capacity;
	private int memberCnt;
	@JsonIgnore
	private Set<WebSocketSession> sessions;
	
	public RoomDTO() {
		super();
		this.memberCnt = 0;
		this.sessions = new HashSet<WebSocketSession>();
	}

	public RoomDTO(String pw, String name, String host, int capacity) {
		super();
		this.id = System.currentTimeMillis()+"";
		this.pw = pw;
		this.name = name;
		this.host = host;
		this.capacity = capacity;
		this.sessions = new HashSet<WebSocketSession>();
		this.memberCnt = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<WebSocketSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<WebSocketSession> sessions) {
		this.sessions = sessions;
	}
	
	
	
	public int getMemberCnt() {
		return memberCnt;
	}

	public void setMemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	public void addSession(WebSocketSession session) {
		sessions.add(session);
	}
	
	public void removeSession(WebSocketSession session) {
		if(sessions.contains(session)) {
			sessions.remove(session);
		}else {
			return;
		}
	}
	
	public void handleMessage(WebSocketSession session, ChatMessage chatMessage) throws Exception {

        if (chatMessage.getType() == ChatMessage.JOIN) {
            addSession(session);
            chatMessage.setMessage(chatMessage.getWriter() + "님이 입장했습니다.");
        }
        
        send(chatMessage);
    }

    private void send(ChatMessage message) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
		}
    }
	
    
	@Override
	public String toString() {
		return "RoomDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", host=" + host + ", capacity=" + capacity
				+ ", sessions=" + sessions + "]";
	}
	
}
