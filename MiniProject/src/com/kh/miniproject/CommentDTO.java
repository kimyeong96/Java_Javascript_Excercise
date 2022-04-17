package com.kh.miniproject;

import java.sql.Date;

public class CommentDTO {

	private String content;
	private String nickname;
	private String comment_date;
	
	public CommentDTO() {}

	public CommentDTO(String content, String nickname, String comment_date) {
		super();
		this.content = content;
		this.nickname = nickname;
		this.comment_date = comment_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getComment_date() {
		return comment_date;
	}

	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}

	@Override
	public String toString() {
		return  content + "\t" + nickname + "\t" + comment_date ;
	}
	
	
	
	
	
}
