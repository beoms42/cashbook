package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.Member;


@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/WEB-INF/view/InsertMember.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		int resultRow = 999;

		
		MemberDao md = new MemberDao();
		try {
			resultRow = md.insertRegister(memberId, memberPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(resultRow == 1) {
			//회원가입 성공
		} else if(resultRow == 0) {
			// 아이디가 이미 있을때
		}
		
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}

}
