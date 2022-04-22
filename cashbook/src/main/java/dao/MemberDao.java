package dao;

import vo.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MemberDao {

	
	/*
	 * 기능상세 : 
	 * 로그인
	 * 가입
	 * 수정
	 * 탈퇴
	 * 정보
	 * 
	 */
	// 가입
	
	public int insertRegister(String memberId, String memberPw) throws Exception {
		
		Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO member (member_id, member_pw, create_date)"
        		+ " VALUES(?, PASSWORD(?), NOW());";
	      
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
        stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		int row  = stmt.executeUpdate();
		
		conn.close();
		stmt.close();
		return row;
	}
	
	public String selectMemberByIdPw(Member member) throws Exception {
		
		String memberId = null;
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT member_id memberId from member where member_id= ? and member_pw = PASSWORD(?)";
	      
        System.out.println(member);
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
        stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		rs  = stmt.executeQuery();
		
		if(rs.next()) {
		memberId = rs.getString("memberId");
		}
		
		conn.close();
		stmt.close();
		rs.close();
		return memberId;
	}
}
