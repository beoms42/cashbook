package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;


@WebServlet("/TagOneController")
public class TagOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashtagDao hd = new HashtagDao();
		List<Map<String, Object>> list = null;
		
		String tag = request.getParameter("tag");
		try {
			list = hd.selectOneTagList(tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/TagOneList.jsp").forward(request, response);
	}


}
