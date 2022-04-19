package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/insertCashbookController")
public class insertCashbookController extends HttpServlet {       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(request.getParameter("m"));
		int d = Integer.parseInt(request.getParameter("d"));
		
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
		
		CashbookDao cd = new CashbookDao();
		
		try {
			cd.insertByDay(y, m, d, cash, memo, kind);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}
}
