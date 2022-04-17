package com.kh.miniproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;


public class PostDAO {

	private BasicDataSource bds = new BasicDataSource();
	private static PostDAO instance = null;
	
	public PostDAO() {  
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10);
	}
	
	public static PostDAO getInstance(){
		if(instance == null) {
			instance = new PostDAO();
		}
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	
	// 1. 포스트 등록
	public int insert(PostDTO dto) throws Exception {  
		String sql = "insert into post_tbl values(seq_post.nextval, ?, ?, ?, sysdate)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getNickname());
			
			int rs = pstmt.executeUpdate();
			return rs;
			
		}
	}
	
	
	// 2. 전체 목록을 보여줌
	public ArrayList<PostDTO> selectAll() throws Exception{
			String sql = "select * from post_tbl";
			try(Connection con = this.getConnection();			
				PreparedStatement pstmt = con.prepareStatement(sql)){
				
				ResultSet rs = pstmt.executeQuery();
				
				ArrayList<PostDTO> list = new ArrayList<>();
				while(rs.next()) {
					int no = rs.getInt(1);
					String title = rs.getString(2);
					String nickname = rs.getString(4);
					String register_date = parseDate(rs.getDate(5));
					
					list.add(new PostDTO(no, title, nickname, register_date));	
				}
				return list;
			}	
		}
		
	
	// 3. 포스트 확인 
	public PostDTO select(int id) throws Exception{
		
		String sql = "select * from post_tbl where no = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String register_date = parseDate(rs.getDate(5));
				String nickname = rs.getString(4);
				PostDTO dto = new PostDTO(no, title, content, register_date, nickname);
				return dto;
			}
			return null;
		}
	}
	
	// 4. 포스트 수정
	public int update(PostDTO dto) throws Exception {

		String sql = "update post_tbl set title = ?, content = ? where no = ?";
		try (Connection con = this.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());

			int rs = pstmt.executeUpdate();
			return rs;
		}

	}
		
	// 5. 포스트 삭제
	public int delete(int no) throws Exception{ 
		String sql = "delete from post_tbl where no = ?";
			
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
				
			pstmt.setInt(1, no);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
		
		
	// Date -> String 으로 변환
	public String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
		return sdf.format(date);
	}
}
