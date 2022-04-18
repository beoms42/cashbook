package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;


@WebServlet("/CashbookListByMonthController")
public class CashbookListByMonthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) ���� ����� ����Ʈ ��ûó��
		int y = 0;
		int m = 0;
		
		Calendar today = Calendar.getInstance();
		
		y = today.get(Calendar.YEAR);
		m = today.get(Calendar.MONTH) + 1 ; // 0�� 1�� , 1�� 2���̱ⶫ��
		
		if(request.getParameter("y") != null) {
			y=Integer.parseInt(request.getParameter("y"));
		}
		
		if(request.getParameter("m") != null) {
			m=Integer.parseInt(request.getParameter("m"));
		}
		
		if(m==0) {
			m = 12;
			y = y-1;
		}
		if(m==13) {
			m = 1;
			y = y+1;
		}

		System.out.println(y+"<--------y");
		System.out.println(m+"<--------m");
		// 2) �𵨰��� ȣ���� ���� ��ȯ�޴� �����Ͻ� ����
		
		CashbookDao cd = new CashbookDao();
		List<Map<String, Object>> list = cd.selectCashbookListByMonth(y, m);
		request.setAttribute("list", list);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		// 3) �� ������
		request.getRequestDispatcher("/WEB-INF/view/CashbookListByMonthView.jsp").forward(request, response);
	}


}
