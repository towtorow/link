package com.linker.link.mapper;

import java.util.ArrayList;

import com.linker.link.member.MemberDTO;

public interface MemberMapper {
	public MemberDTO getMember(String id);
	public ArrayList<MemberDTO> getMembers();
	public int createMember(MemberDTO member);
	public int updateMember(MemberDTO member);
	public int deleteMember(String id);
}
