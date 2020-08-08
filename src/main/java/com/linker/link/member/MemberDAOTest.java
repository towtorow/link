package com.linker.link.member;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberDAOTest {

	public static void main(String[] args) throws Exception {

		ApplicationContext a = new ClassPathXmlApplicationContext("spring/root-context.xml");
		MemberDAO memberDao = (MemberDAO)a.getBean("memberDAO");
		
		MemberDTO member = new MemberDTO("bb@bb.com","bb");
		
		memberDao.createMember(member);

		MemberDTO selectedMember = memberDao.getMember("bb@bb.com");
		System.out.println(selectedMember.toString());
		
	}

}
