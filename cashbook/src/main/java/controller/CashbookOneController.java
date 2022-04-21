package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		// 셀렉트 원 해서 받는 결과 vo
		Cashbook list = null;
		CashbookDao cd = new CashbookDao();
		list = cd.selectCashbookOne(cashbookNo);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/CashBookOne.jsp").forward(request, response);
		
	}

}
