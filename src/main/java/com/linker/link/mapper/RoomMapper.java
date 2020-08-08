package com.linker.link.mapper;

import java.util.ArrayList;

import com.linker.link.room.RoomDTO;


public interface RoomMapper {
	public RoomDTO getRoom(String id);
	public ArrayList<RoomDTO> getRooms();
	public int createRoom(RoomDTO room);
	public int updateRoom(RoomDTO room);
	public int deleteRoom(String id);
}
