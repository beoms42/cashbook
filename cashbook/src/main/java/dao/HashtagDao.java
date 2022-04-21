package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class HashtagDao {
	public List<Map<String, Object>> selectTagRankList() throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

		/*
			SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking
			FROM 
			(SELECT tag, COUNT(*) cnt
			FROM hashtag
			GROUP BY tag) t;
		 * */
		
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) rank"
        		+ "			FROM "
        		+ "			(SELECT tag, COUNT(*) cnt"
        		+ "			FROM hashtag"
        		+ "			GROUP BY tag) t;";
	      
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
        stmt = conn.prepareStatement(sql);
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tag", rs.getString("tag"));
			map.put("cnt", rs.getString("t.cnt"));
			map.put("rank", rs.getString("rank"));
			
			list.add(map);
		}
		
		return list;
	
	}
	
		public List<Map<String, Object>> selectOneTagList(String tag) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		/*
			SELECT h.tag, c.cashbook_no, c.kind, c.memo, c.cash
			FROM hashtag h
			INNER JOIN cashbook c
			ON h.cashbook_no = c.cashbook_no
			WHERE tag = '학원비'; 
		 * */
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT h.tag, c.cashbook_no, c.kind, c.memo, c.cash"
        		+ "			FROM hashtag h"
        		+ "			INNER JOIN cashbook c"
        		+ "			ON h.cashbook_no = c.cashbook_no"
        		+ "			WHERE tag = ? ";
	      
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, tag);
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tag", rs.getString("h.tag"));
			map.put("cashbookNo", rs.getInt("c.cashbook_no"));
			map.put("kind", rs.getString("c.kind"));
			map.put("memo", rs.getString("c.memo"));
			map.put("cash", rs.getInt("c.cash"));
			list.add(map);
		}
		
		return list;
	
	}
		
	public List<Map<String, Object>> SearchKindList(String kind, String beginDate, String endDate) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		/*
			SELECT t.kind kind, t.tag tag, t.cash_date cashDate
			FROM
			(SELECT 
			h.tag, 
			kind, 
			c.cash_date
			FROM hashtag h INNER JOIN cashbook c
			ON h.cashbook_no = c.cashbook_no) t
			WHERE tag = '학원비' AND cash_date BETWEEN '' AND '2022-01-30'
			GROUP BY kind, tag;
		 * */
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT t.kind kind, t.tag tag, t.cash_date cashDate"
        		+ "			FROM"
        		+ "			(SELECT "
        		+ "			h.tag, "
        		+ "			kind, "
        		+ "			c.cash_date"
        		+ "			FROM hashtag h INNER JOIN cashbook c"
        		+ "			ON h.cashbook_no = c.cashbook_no) t"
        		+ "			WHERE kind = ? AND cash_date BETWEEN ? AND ?"
        		+ "			GROUP BY kind, tag;";
	      
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, kind);
        stmt.setString(2, beginDate);
        stmt.setString(3, endDate);
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("kind", rs.getString("kind"));
			map.put("tag", rs.getString("tag"));
			map.put("cashDate", rs.getString("cashDate"));
				
			list.add(map);
			}
			
			return list;
		}
}
