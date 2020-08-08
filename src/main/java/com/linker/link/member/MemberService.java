package com.linker.link.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {
	
	private MemberDAO memberDao;



	private MemberService() {

	}


	


	public MemberDAO getMemberDao() {
		return memberDao;
	}



	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}



	public int signin(MemberDTO member) {

		int ret = 0;
		try {
			if (!memberDao.isExisted(member.getId())) {

				ret = memberDao.createMember(member);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return ret;

	}



	public MemberDTO login(String id, String pw) {
		MemberDTO ret = null;
		MemberDTO member=null;
		try {
			member = memberDao.getMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		if (member != null && member.isPwMatched(pw)) {

			ret = member;
		}

		return ret;

	}



	public int deleteMember(String id) throws Exception {

		return memberDao.deleteMember(id);

	}



	public int updateMember(MemberDTO member) throws Exception {

		return memberDao.updateMember(member);

	}



	public MemberDTO getMember(String id) throws Exception {

		return memberDao.getMember(id);

	}



	public ArrayList<MemberDTO> getUserList() throws Exception {

		return memberDao.getMembers();

	}
}
