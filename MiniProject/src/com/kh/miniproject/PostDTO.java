package com.kh.miniproject;



public class PostDTO {
	private int no;
	private String title;
	private String content;
	private String nickname;
	private String register_date;
	
	
	public PostDTO() {}
	
	// 수정시 필요
	public PostDTO(int no, String title,String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}
	
	// 목록 확인시 필요
	public PostDTO(int no, String title,String nickname, String register_date) {
		this.no = no;
		this.title = title;
		this.nickname = nickname;
		this.register_date = register_date;
	} 
	
	
	public PostDTO(int no, String title, String content, String register_date, String nickname) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.register_date = register_date;
		this.nickname = nickname;
	}
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return no +"\t" + title + "\t" + nickname + "\t" + register_date;

	}
	
}
