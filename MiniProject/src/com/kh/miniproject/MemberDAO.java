package com.kh.miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp2.BasicDataSource;

public class MemberDAO {
	
	private BasicDataSource bds = new BasicDataSource();
	
	private static MemberDAO instance = null;
	public MemberDAO() {
        bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        bds.setUsername("kh");
        bds.setPassword("kh");
        bds.setInitialSize(10);
    }
	
	public static MemberDAO getInstance() {
		if(instance == null) {
		return new MemberDAO();
		}	return instance;
	}
 
    public Connection getConnection() throws Exception {
        return bds.getConnection();
    }
    
    public boolean loginCheck(String id, String pw)throws Exception { // 로그인 가능여부
    	String sql = "select * from member_tbl where id=?and pw=?";
    	try(Connection con = getConnection();
    		PreparedStatement pstmt = con.prepareStatement(sql);){
    		
    		pstmt.setString(1, id);
    		pstmt.setString(2, pw);
    		ResultSet rs = pstmt.executeQuery();
    		if(rs.next()) {
    			return true;
    		}
    		return false;
    		
    	}
    }
    
    
    public boolean idCheck(String id) throws Exception { // 아이디 중복확인

		String sql = "select * from member_tbl where id = ?";
		try (Connection con = this.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			} 
			return true;
		}
	}

	public boolean nicknameCheck(String nickname) throws Exception { // 닉네임 중복확인
		String sql = "select * from member_tbl where nickname = ?";
		try (Connection con = this.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, nickname);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			} 
			return true;
		}
	}
	
    public String getNickname(String id)throws Exception { // 닉네임 가져오기 
    	String sql = "select nickname from member_tbl where id=?";
    	try(Connection con = getConnection();
    		PreparedStatement pstmt = con.prepareStatement(sql); ){
    	
    	pstmt.setString(1, id);
    	ResultSet rs = pstmt.executeQuery();
    	if(rs.next()) {
    		return rs.getString(1);
    	}
    	return null;
    	}
    }

	public int insert(MemberDTO dto) throws Exception { // 회원가입
		String sql = "insert into member_tbl values(?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getNickname());

			int rs = pstmt.executeUpdate();

			return rs;
		}

	}

}
