package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import dao.HashtagDao;


@WebServlet("/TagController")
public class TagController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 여부 확인 로직(세션이용)
		   HttpSession session = request.getSession();
		   String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		   if(sessionMemberId == null) {
				// 이미 로그인이 되어 있는 상태라면
			   response.sendRedirect(request.getContextPath()+"/LoginController");
			}
		   
		HashtagDao hd = new HashtagDao();
		List<Map<String, Object>> list = null;
		try {
			list = hd.selectTagRankList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/TagList.jsp").forward(request, response);
	}


}
