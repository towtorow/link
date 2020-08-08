package com.linker.link;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linker.link.chat.ChatMessage;
import com.linker.link.room.RoomDTO;
import com.linker.link.room.RoomRepository;
import com.linker.link.room.RoomService;

public class EchoHandler extends TextWebSocketHandler {
	@Autowired
	private RoomRepository roomRepository;

	public EchoHandler() {
		super();
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public void setRoomRepository(RoomRepository repository) {
		this.roomRepository = repository;
		System.out.println(this.roomRepository + " in handler");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String query = session.getUri().getQuery();
		String roomId = query;
		System.out.println("roomid in afterConnectionEstablished : " + roomId);
		RoomDTO room = roomRepository.getRoom(roomId);
		room.setMemberCnt(room.getMemberCnt() + 1);
		roomRepository.updateRoom(room);
		if (room.getMemberCnt() > room.getCapacity()) {
			session.close();
		}
		room.addSession(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ChatMessage chatMessage = mapper.readValue(message.getPayload().toString(), ChatMessage.class);
		RoomDTO room = roomRepository.getRoom(chatMessage.getRoomId());
		room.handleMessage(session, chatMessage);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String query = session.getUri().getQuery();
		String roomId = query;
		System.out.println("roomid in afterConnectionClosed : " + roomId);
		RoomDTO room = roomRepository.getRoom(roomId);
		room.setMemberCnt(room.getMemberCnt() - 1);
		roomRepository.updateRoom(room);
		roomRepository.removeSession(session);

	}
}
