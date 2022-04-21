package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

@WebServlet("/TagSearchController")
public class TagSearchController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String kind = request.getParameter("kind");
		String endDate = request.getParameter("endDate");
		String beginDate = request.getParameter("beginDate");
		List<Map<String, Object>> list = null;
		HashtagDao hd = new HashtagDao();
		try {
			list = hd.SearchKindList(kind, beginDate, endDate);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		System.out.println(list);
		request.getRequestDispatcher("/WEB-INF/view/TagSearch.jsp").forward(request, response);
	}

}
