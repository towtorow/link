package com.linker.link.chat;

public class ChatMessage {
	public static final int JOIN = 1;
	private String roomId;
    private String writer;
    private String message;
    private int type;
	
    public ChatMessage() {
		super();
	}

	public ChatMessage(String roomId, String writer, String message, int type) {
		super();
		this.roomId = roomId;
		this.writer = writer;
		this.message = message;
		this.type= type;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ChatMessage [roomId=" + roomId + ", writer=" + writer + ", message=" + message + ", type=" + type + "]";
	}
    
}
