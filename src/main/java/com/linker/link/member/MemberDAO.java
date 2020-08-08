package com.linker.link.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.linker.link.mapper.MemberMapper;

public class MemberDAO {
	
	private MemberMapper memberMapper;

	public MemberMapper getMemberMapper() {
		return memberMapper;
	}

	public void setMemberMapper(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public MemberDAO() {

	}
	
	
	/*************************  CRUD *******************************/
	public int createMember(MemberDTO member) throws Exception{
		return memberMapper.createMember(member);
	}
	
	public int updateMember(MemberDTO member) throws Exception{
		return memberMapper.updateMember(member);
	}
	
	public int deleteMember(String id) throws Exception{
		return memberMapper.deleteMember(id);
	}
	
	public MemberDTO getMember(String id) throws Exception{
		System.out.println(memberMapper);
		return memberMapper.getMember(id);
	}
	
	public ArrayList<MemberDTO> getMembers() throws Exception{
		return memberMapper.getMembers();
	}
	
	public boolean isExisted(String id) throws Exception {
		boolean ret = false;
		if(getMember(id) != null) {
			ret = true;
		}
		return ret;
	}
}
