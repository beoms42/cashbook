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
  	
    
	// ���� : ����ڰ� ��û�� �Ҷ������� ������
	// �ֱ������� ��û�� ����� �ҵ�
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
			// �α��� ���н� �α������� ���û
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		} 
		// ���� ������ client(browser)�� ���ǰ��� �޾��� �޾Ƽ� httpsession ��ü�� ����� ����
			HttpSession session = request.getSession();
			session.setAttribute("sessionMemberId", session);
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		 
			
		  // 1) ���� ����� ����Ʈ ��û �м�
	      Calendar now = Calendar.getInstance(); // ex) 2022.04.19
	      int y = now.get(Calendar.YEAR);
	      int m = now.get(Calendar.MONTH) + 1; // 0 - 1��, 1 - 2��, ... 11 - 12��
	}

}
