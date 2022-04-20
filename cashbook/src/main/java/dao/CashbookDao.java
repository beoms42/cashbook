package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import vo.Cashbook;

public class CashbookDao {
   public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      /*
       SELECT 
          cashbook_no cashbookNo
          ,DAY(cash_date) day
          ,kind
          ,cash
       FROM cashbook
       WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
       ORDER BY DAY(cash_date) ASC
       */
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql = "SELECT"
            + "          cashbook_no cashbookNo"
            + "          ,DAY(cash_date) day"
            + "          ,kind"
            + "          ,cash"
            + "          ,CONCAT(LEFT(memo, 5), '...') memo"
            + "       FROM cashbook"
            + "       WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
            + "       ORDER BY DAY(cash_date) ASC, kind asc";
      try {
         Class.forName("org.mariadb.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, y);
         stmt.setInt(2, m);
         rs = stmt.executeQuery();
         while(rs.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cashbookNo", rs.getInt("cashbookNo"));
            map.put("day", rs.getInt("day"));
            map.put("kind", rs.getString("kind"));
            map.put("cash", rs.getInt("cash"));
            map.put("memo", rs.getString("memo"));
            list.add(map);
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("asdasdsdsadsa");
      } finally {
         try {
            rs.close();
            stmt.close();
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("2323232323");
         }
      }
      return list;
   }
   
   public void insertByDay(int y, int m, int d, int cash, String memo, String kind, List<String> hashtag) {
	   System.out.println("dao시작");
	   /* SQL구문
	    INSERT INTO cashbook
		VALUES (
			NULL,
			'수입',
			'플레이스테이션 구매',
			575000,
			NOW(),
			NOW(),
			NOW()
		);
	    */
	   
	   String day = y+"-"+m+"-"+d;
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   
	   String sql = "INSERT INTO cashbook(kind, memo ,cash ,cash_date,update_date,create_date)"
				+ " VALUES(?,?,?,?,NOW(),NOW())";
	   
	   try {
	   Class.forName("org.mariadb.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	   conn.setAutoCommit(false);
	   System.out.println("제네레이트 밑시작");
	   stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // INSERT + SELECT 방금 생성된 키값 반환
	   System.out.println("제네레이트 끝");
	   // ex) 방금 select 입력한 cashbook_no from cashbook 실행하면 
	   stmt.setString(1, kind);
	   stmt.setString(2, memo);
	   stmt.setInt(3, cash);
	   stmt.setString(4, day);
	   
	   stmt.executeUpdate();
	   rs = stmt.getGeneratedKeys(); // 방금 select 입력한 cashbook no from cashbook
	   int cashbookNo = 0;
	   
	   
	   if(rs.next()) {
		   cashbookNo = rs.getInt(1);
		   System.out.println(cashbookNo+"<---------- 캐시북넘버");
	   }
	   
	   for(String g : hashtag) {
		   PreparedStatement stmt2 = null;
		   String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date)"
				   +" VALUES(?, ?, NOW())";
		   stmt2 = conn.prepareStatement(sql2);
		   stmt2.setInt(1, cashbookNo);
		   stmt2.setString(2, g);
		   
		   
		   stmt2.executeUpdate();

	   }
	   
	   
	   
	   conn.commit();
	   } catch (Exception e) {
		    try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
	   } finally {
	         try {
	             rs.close();
	             stmt.close();
	             conn.close();
	          } catch (SQLException e) {
	             e.printStackTrace();
	          }
	   
	   
	   }
   }
   
}