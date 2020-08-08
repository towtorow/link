package com.linker.link.member;



public class MemberDTO{
	private String id;
	private String pw;
	
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	public boolean isPwMatched(String password) {

		boolean ret = false;

		if (pw.equals(password)) {

			ret = true;

		}

		return ret;

	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + "]";
	}
	
	
}
