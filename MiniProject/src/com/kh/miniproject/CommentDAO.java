package com.kh.miniproject;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class CommentDAO {

	private BasicDataSource bds = new BasicDataSource();
	private static CommentDAO instance = null;
	
	public CommentDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10);
	}
	
	public static CommentDAO getInstance(){
		if(instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	} 

	public int insert(CommentDTO dto) throws Exception{ // 댓글 입력 
		
		String sql = "insert into comment_tbl values(?, ?, sysdate)";
	
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getContent());
			pstmt.setString(2, dto.getNickname());
			
			int rs = pstmt.executeUpdate();
			
			return rs;
		}
		
	
	}
	
	public ArrayList<CommentDTO> selectAll() throws Exception{  // 댓글 조회
		String sql = "select * from comment_tbl";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<CommentDTO> list = new ArrayList<>();
			
			while (rs.next()) {
				String content = rs.getString(1);
				String nickname = rs.getString(2);
				String date = parseDate(rs.getDate(3));
				
				list.add(new CommentDTO(content,nickname,date));
			}
			return list;
		} 
	}
	
	public String parseDate(Date date) {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
		String rs = sdf.format(date);
		return rs;
	}
	
	
	
}
