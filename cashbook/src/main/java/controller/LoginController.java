package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
  	
    
	// 세션 : 사용자가 요청을 할때까지만 유지함
	// 주기적으로 요청을 해줘야 할듯
	// 
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String sessionMemberId = null;
		Member mem = new Member();
		mem.setMemberId(memberId);
		mem.setMemberPw(memberPw);
		
		MemberDao md = new MemberDao();
		try {
			sessionMemberId = md.selectMemberByIdPw(mem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(sessionMemberId == null) {
			// 로그인 실패시 로그인폼을 재요청
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		} 
		// 현재 연결한 client(browser)의 세션값을 받아을 받아서 httpsession 객체를 만들어 저장
			HttpSession session = request.getSession();
			session.setAttribute("sessionMemberId", session);
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		 
			
		  // 1) 월별 가계부 리스트 요청 분석
	      Calendar now = Calendar.getInstance(); // ex) 2022.04.19
	      int y = now.get(Calendar.YEAR);
	      int m = now.get(Calendar.MONTH) + 1; // 0 - 1월, 1 - 2월, ... 11 - 12월
	}

}
