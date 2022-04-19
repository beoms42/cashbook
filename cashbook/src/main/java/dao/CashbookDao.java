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
      } finally {
         try {
            rs.close();
            stmt.close();
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return list;
   }
   
   public void insertByDay(int y, int m, int d, int cash, String memo, String kind) {
	   
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
	   
	   String sql = "INSERT INTO cashbook "
	   		+ "		VALUES ( "
	   		+ "			NULL, "
	   		+ "			?, "
	   		+ "			?, "
	   		+ "			?, "
	   		+ "			?, "
	   		+ "			NOW(), "
	   		+ "			NOW() "
	   		+ "		)";
	   
	   try {
	   Class.forName("org.mariadb.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	   
	   stmt = conn.prepareStatement(sql);
	   stmt.setString(1, kind);
	   stmt.setString(2, memo);
	   stmt.setInt(3, cash);
	   stmt.setString(4, day);
	   
	   int row = stmt.executeUpdate();
	   
	   } catch (Exception e) {
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