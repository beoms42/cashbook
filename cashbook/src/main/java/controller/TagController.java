package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import dao.HashtagDao;


@WebServlet("/TagController")
public class TagController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
