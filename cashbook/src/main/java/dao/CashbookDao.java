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

import javax.servlet.http.HttpSession;

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
   
   public void insertByDay(int y, int m, int d, int cash, String memo, String kind, List<String> hashtag, String sessionMemId) {
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
	   
	   String sql = "INSERT INTO cashbook(kind, memo ,cash ,cash_date,update_date,create_date, member_id)"
				+ " VALUES(?,?,?,?,NOW(),NOW(),?)";
	   
	   try {
	   Class.forName("org.mariadb.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	   conn.setAutoCommit(false);
	   stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // INSERT + SELECT 방금 생성된 키값 반환
	   // ex) 방금 select 입력한 cashbook_no from cashbook 실행하면 
	   stmt.setString(1, kind);
	   stmt.setString(2, memo);
	   stmt.setInt(3, cash);
	   stmt.setString(4, day);
	   stmt.setString(5, sessionMemId);
	   
	   
	   stmt.executeUpdate();
	   rs = stmt.getGeneratedKeys(); // 방금 select 입력한 cashbook no from cashbook
	   int cashbookNo = 0;
	   
	   
	   if(rs.next()) {
		   cashbookNo = rs.getInt(1);
	   }
	   
	   for(String g : hashtag) {
		   PreparedStatement stmt2 = null;
		   String sql2 = "INSERT INTO hashtag(cashbook_no, tag)"
				   +" VALUES(?, ?)";
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
   
   public Cashbook selectCashbookOne(int cashbookNo) {
	   
	   Cashbook cb = new Cashbook();
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   
	   String sql = "SELECT * "
	   		+ " FROM cashbook "
	   		+ " WHERE cashbook_no =  ? ";
	   
	   try {
	   Class.forName("org.mariadb.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	   stmt = conn.prepareStatement(sql);
	   stmt.setInt(1, cashbookNo);

	   
	   rs = stmt.executeQuery();
	   while(rs.next()) {
		   cb.setCashbookNo(cashbookNo);
		   cb.setKind(rs.getString("kind"));
		   cb.setMemo(rs.getString("memo"));
		   cb.setCash(rs.getInt("cash"));
		      
	    }
	   }
	   catch (Exception e) {
		// TODO: handle exception
	   } finally {
	         try {
	        	 
	             rs.close();
	             stmt.close();
	             conn.close();
	          } catch (SQLException e) {
	             e.printStackTrace();
	          }
	   
	         
	   }
	   return cb;
   }
   
   public void deleteCashbookOne(int cashbookNo) {
	   
	   Cashbook cb = new Cashbook();
	   Connection conn = null;
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   
	   String sql2 =  "DELETE FROM hashtag "
		   		+ " WHERE cashbook_no = ? ";
	   
	   String sql = "DELETE FROM cashbook "
	   		+ " WHERE cashbook_no = ? ";
	   
	   try {
	   Class.forName("org.mariadb.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	   conn.setAutoCommit(false);
	   PreparedStatement stmt2 = null;
	   
	   stmt2 = conn.prepareStatement(sql2);
	   stmt2.setInt(1, cashbookNo);
	   stmt = conn.prepareStatement(sql);
	   stmt.setInt(1, cashbookNo);

	   
	   rs = stmt2.executeQuery();
	   
	   rs = stmt.executeQuery();

	   conn.commit();
	   }
	   
	   catch (Exception e) {
		// TODO: handle exception
		   try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
   
   public void updateCashbookOne(int cashbookNo, int cash, String kind, String memo, List<String> hashtag) {
	   
	   Cashbook cb = new Cashbook();
	   Connection conn = null;
	   
	   
	   System.out.println(cashbookNo+"<----------------cashbookNo");
	   System.out.println(cash+"<----------------cash");
	   System.out.println(kind+"<----------------kind");
	   System.out.println(memo+"<----------------memo");
	   System.out.println(hashtag+"<----------------hashtag");
	   /*
	    * 
	    * 1) 해시태그 삭제(델리트에서 썼던 로직 사용)
	    * 2) 내용 업데이트 하고
	    * 3) 해시태그 로직 갔다 쓰면 될듯?
	    */
	   //   * 1) 해시태그 삭제(델리트에서 썼던 로직 사용)
	   String sqlDel =  "DELETE FROM hashtag "
		   		+ " WHERE cashbook_no = ? ";
	   
	   // 2) 내용 업데이트 하고
	   String sqlUp = "UPDATE cashbook"
	   		+ " SET cash = ?, kind = ?, memo = ?, update_date = now()"
	   		+ " WHERE cashbook_no = ?";
	   
//	   // * 3) 해시태그 로직 갔다 쓰면 될듯?
	   String sql3 = "INSERT INTO hashtag(cashbook_no, tag, create_date)"
			   +" VALUES(?, ?, NOW())";
	   
	   try {
	   Class.forName("org.mariadb.jdbc.Driver");
	   conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
	   conn.setAutoCommit(false);
	   
	   PreparedStatement tagDelstmt = conn.prepareStatement(sqlDel);
	   PreparedStatement updateStmt = conn.prepareStatement(sqlUp);
	   
	   tagDelstmt.setInt(1, cashbookNo);
	   
	   updateStmt.setInt(1, cash);
	   updateStmt.setString(2, kind);
	   updateStmt.setString(3, memo);
	   updateStmt.setInt(4, cashbookNo);
	   
	   System.out.println("====================2===========");
	   tagDelstmt.executeUpdate();
	   updateStmt.executeUpdate();

	   System.out.println("====================3===========");
	   for(String g : hashtag) {
		   PreparedStatement stmt3 = null;
		  
		   stmt3 = conn.prepareStatement(sql3);
		   stmt3.setInt(1, cashbookNo);
		   stmt3.setString(2, g);
		   System.out.println("====================4===========");
		   
		   stmt3.executeUpdate();
	   }
	   
	   conn.commit();
	   }
	   
	   catch (Exception e) {
		// TODO: handle exception
		   try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   } finally {
	         try {
	        	
	             conn.close();
	          } catch (SQLException e) {
	             e.printStackTrace();
	          }
	   
	         
	   }
   }
		   
}