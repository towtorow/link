package com.linker.link.room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

public class RoomRepository {
	
	
	private RoomService roomService;
	
	private Map<String, RoomDTO> roomMap;
    public RoomRepository() {
    	System.out.println("roomRepository create");
    	roomMap = new HashMap<String, RoomDTO>();
    }
    
    public RoomDTO getRoom(String id) {
    	System.out.println(roomMap);
    	System.out.println("id : " + id);
    	System.out.println(roomMap.get(id));
    	System.out.println(id + " : " + ((RoomDTO)roomMap.get(id)).toString());
        return (RoomDTO)roomMap.get(id);
    }
    
    public Collection<RoomDTO> getRooms() {
        return (Collection<RoomDTO>)roomMap.values();
    }
    
    public void removeRoom(String id) {
    	roomMap.remove(id);
    }
    
    public void addRoom(RoomDTO room) {
    	roomMap.put(room.getId(), room);
    	System.out.println("roomMap put success : "+roomMap.get(room.getId()));
    }
    
    public void updateRoom(RoomDTO room) {
    	try {
			roomService.updateRoom(room);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	removeRoom(room.getId());
    	addRoom(room);
    }
    
    public void removeSession(WebSocketSession session) {
    	ArrayList<RoomDTO> rooms = new ArrayList<RoomDTO> (getRooms());
    	for (RoomDTO room : rooms) {
			room.removeSession(session);
		}
    }

    public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
		System.out.println(this.roomService+" in repository");
		ArrayList<RoomDTO> rooms = null;
		try {
			rooms = roomService.getRooms();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rooms != null) {
     		for (RoomDTO room : rooms) {
     			roomMap.put(room.getId(), room);
     		}
        }
	}
	
}
