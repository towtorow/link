package com.linker.link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linker.link.member.MemberDAO;
import com.linker.link.room.RoomDTO;
import com.linker.link.room.RoomRepository;
import com.linker.link.room.RoomService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomService roomService;

	public ChatController() {
	}

	public RoomRepository getRoomRepository() {
		return roomRepository;
	}

	public void setRoomRepository(RoomRepository repository) {
		this.roomRepository = repository;
		System.out.println(this.roomRepository + " in controller");
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
		System.out.println(this.roomService + " in controller");
	}

	@RequestMapping(value = { "/roomsGrid" })
	public String roomsGrid() {
		System.out.println("enter chatcontroller roomsgrid");
		return "roomsGrid";

	}

	@RequestMapping(value = { "/roomCreateForm" })
	public String roomCreateForm() {
		return "roomCreateForm";
	}

	@RequestMapping("/rooms")
	public @ResponseBody ArrayList<RoomDTO> rooms() {
		try {
			return new ArrayList<RoomDTO>(roomRepository.getRooms());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/rooms/{id}")
	public @ResponseBody RoomDTO room(@PathVariable String id) {

		return roomRepository.getRoom(id);

	}

	@RequestMapping("/room")
	public String enterRoom(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		System.out.println(id);
		return "room";
	}

	@ResponseBody
	@RequestMapping(value = {
			"/room/delete" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {
					"Accept=application/json" })
	public int delete(@RequestBody RoomDTO room) {
		String id = room.getId();
		System.out.println("delete room" + id);
		roomRepository.removeRoom(id);
		int ret = 0;
		try {
			ret = roomService.deleteRoom(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = {
			"/room/create" }, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {
					"Accept=application/json" })
	public int create(@RequestBody RoomDTO room) {
		int ret = 0;
		try {
			System.out.println(room.getName());
			room.setId(System.currentTimeMillis() + "");
			System.out.println("id set success : " + room.getId());
			roomRepository.addRoom(room);
			ret = roomService.createRoom(room);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@ResponseBody
	@RequestMapping("/room/update")
	public int update(@RequestBody RoomDTO room) throws Exception {
		roomRepository.updateRoom(room);
		return roomService.updateRoom(room);
	}

	@RequestMapping("/roomModifyForm")
	public String roomModifyForm(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "roomModifyForm";
	}

}