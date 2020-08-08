package com.linker.link.room;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.linker.link.mapper.RoomMapper;

public class RoomDAO {
	
	private RoomMapper roomMapper;

	public RoomMapper getMemberMapper() {
		return roomMapper;
	}

	public void setRoomMapper(RoomMapper roomMapper) {
		this.roomMapper = roomMapper;
		System.out.println(this.roomMapper +" in dao");
	}
	
	public RoomDAO() {
		
	}
	
	
	/*************************  CRUD *******************************/
	public int createRoom(RoomDTO room) throws Exception{
		return roomMapper.createRoom(room);
	}
	
	public int updateRoom(RoomDTO room) throws Exception{
		return roomMapper.updateRoom(room);
	}
	
	public int deleteRoom(String id) throws Exception{
		return roomMapper.deleteRoom(id);
	}
	
	public RoomDTO getRoom(String id) throws Exception{
		return roomMapper.getRoom(id);
	}
	
	public ArrayList<RoomDTO> getRooms() throws Exception{
		return roomMapper.getRooms();
	}	
}
