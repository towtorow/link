package com.linker.link.room;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class RoomService{
	
	private RoomDAO roomDao;
	
    private RoomService(){
    	
    }
    

    
    
	public RoomDAO getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDAO roomDao) {
		this.roomDao = roomDao;
		System.out.println(this.roomDao +" in service");
	}

	public RoomDTO getRoom(String id) throws Exception{
		return roomDao.getRoom(id);
	}
	
	public ArrayList<RoomDTO> getRooms() throws Exception {
    	return roomDao.getRooms();
    }
    
    public int createRoom(RoomDTO room) throws Exception {
    	System.out.println("database insert start");
    	return roomDao.createRoom(room);
    }
    
    public int updateRoom(RoomDTO room) throws Exception {
    	return roomDao.updateRoom(room);
    }
    
    public int deleteRoom(String id) throws Exception {
    	System.out.println("delete room start");
    	return roomDao.deleteRoom(id);
    }
}
