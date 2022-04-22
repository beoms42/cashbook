package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�α��� ���� Ȯ�� ����(�����̿�)
		   HttpSession session = request.getSession();
		   String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		   if(sessionMemberId == null) {
				// �̹� �α����� �Ǿ� �ִ� ���¶��
			   response.sendRedirect(request.getContextPath()+"/LoginController");
			}
		   
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		CashbookDao cd = new CashbookDao();
		cd.deleteCashbookOne(cashbookNo);
		
		response.sendRedirect("CashbookListByMonthController");
	}
	
	

}
