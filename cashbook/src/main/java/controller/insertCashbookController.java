package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/insertCashbookController")
public class insertCashbookController extends HttpServlet {       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(request.getParameter("m"));
		int d = Integer.parseInt(request.getParameter("d"));
		
		// 월, 일에 0이 안붙어도 string을 마리아디비가 처리해줌
		int[] today = { y, m, d };
		
		request.setAttribute("today", today);
		request.getRequestDispatcher("insertCashbookForm.jsp").forward(request, response);
	}
	 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(request.getParameter("m"));
		int d = Integer.parseInt(request.getParameter("d"));
		
		int cash = Integer.parseInt(request.getParameter("cash"));
		String kind = request.getParameter("kind");
		String memo = request.getParameter("memo"); 
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		
		CashbookDao cd = new CashbookDao();
		
		
		// ----------MEMO에 들어간 해시태그를 분리하는 로직 ------------------- //
		
		List<String> hashtag = new ArrayList<>();
		String memo2= memo.replace("#", " #");
		String[] arr = memo2.split(" ");
		
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp = s.replace("#", "");
				if(!temp.equals("")) { // 공백이슈 해결
					hashtag.add(temp);
				} 
			}
		}
		
			cd.insertByDay(y, m, d, cash, memo, kind, hashtag, sessionMemberId);
			
			
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}
}
