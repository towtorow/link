package com.linker.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linker.link.member.MemberDTO;
import com.linker.link.member.MemberService;
import com.linker.link.room.RoomService;

@Controller
public class MemberController {

	@Autowired
    private MemberService memberService;

    public MemberController() {
    }

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = {"/", "/home"})
    public String home() {
        return "home";        
    }
    
	@ResponseBody
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json"})
    public MemberDTO login(@RequestBody MemberDTO member) {
    	return memberService.login(member.getId(), member.getPw());
    }
	
	@ResponseBody
    @RequestMapping(value = {"/signin"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json"})
    public int signin(@RequestBody MemberDTO member) {	
		return memberService.signin(member);
    }
	
    @RequestMapping("/signinForm")
    public String signinForm() {	
		return "signin";
    }
    
}