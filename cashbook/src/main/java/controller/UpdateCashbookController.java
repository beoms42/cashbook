package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/UpdateCashbookController")
public class UpdateCashbookController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 여부 확인 로직(세션이용)
		   HttpSession session = request.getSession();
		   String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		   if(sessionMemberId == null) {
				// 이미 로그인이 되어 있는 상태라면
			   response.sendRedirect(request.getContextPath()+"/LoginController");
			}
		   
		request.setCharacterEncoding("utf-8");
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		
		CashbookDao cd = new CashbookDao();
		Cashbook cb = new Cashbook();
		cb = cd.selectCashbookOne(cashbookNo);
		
		int cash = cb.getCash();
		String kind = cb.getKind();
		String memo = cb.getMemo();
		
		request.setAttribute("cashbookNo", cashbookNo);
		request.setAttribute("cash", cash);
		request.setAttribute("kind", kind);
		request.setAttribute("memo", memo);
		
		request.getRequestDispatcher("/WEB-INF/view/UpdateCashBook.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		int cash = Integer.parseInt(request.getParameter("cash"));
		String kind = request.getParameter("kind");
		String memo = request.getParameter("memo");
		

		// ----------MEMO에 들어간 해시태그를 분리하는 로직 ------------------- //
		
		List<String> hashtag = new ArrayList<>();
		String memo2= memo.replace("#", " #");
		String[] arr = memo2.split(" ");
		
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp = s.replace("#", "");
				if(!temp.equals("")) { // 공백이슈 해결
					hashtag.add(temp);
				} 
			}
		}
				
		CashbookDao cd = new CashbookDao();
		cd.updateCashbookOne(cashbookNo, cash, kind, memo, hashtag);
		
		response.sendRedirect("CashbookListByMonthController");
	}
	
}
