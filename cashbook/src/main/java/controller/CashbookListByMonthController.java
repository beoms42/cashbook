package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

@WebServlet("/CashbookListByMonthController")
public class CashbookListByMonthController extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   
	   //�α��� ���� Ȯ�� ����(�����̿�)
	   HttpSession session = request.getSession();
	   String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	   if(sessionMemberId == null) {
			// �̹� �α����� �Ǿ� �ִ� ���¶��
		   response.sendRedirect(request.getContextPath()+"/LoginController");
		}

      // 1) ���� ����� ����Ʈ ��û �м�
      Calendar now = Calendar.getInstance(); // ex) 2022.04.19
      int y = now.get(Calendar.YEAR);
      int m = now.get(Calendar.MONTH) + 1; // 0 - 1��, 1 - 2��, ... 11 - 12��
      
      int today = now.get(Calendar.DATE); // ���� DAY -> 19
      
      
      if(request.getParameter("y") != null) {
         y = Integer.parseInt(request.getParameter("y"));
      }
      if(request.getParameter("m") != null) {
         m = Integer.parseInt(request.getParameter("m"));
      }
      if(m==0) {
         m = 12;
         y = y-1;
      }
      if(m==13) {
         m = 1;
         y = y+1;
      }
      
      
      /*
       
       1) startBlank
       2) endDay
       3) endBlank
       4) totalBlank
       
       */
      
      // ���۽� �ʿ��� ���� <TD>�� ������ ���ϴ� �˰��� -> startBlank 
      // firstDay�� ���ó�¥�� �������Ͽ� ��¥�� 1�Ϸ� �����ؼ� ������
      Calendar firstDay = Calendar.getInstance(); // ex) 2022.04.19
      firstDay.set(Calendar.YEAR, y);
      firstDay.set(Calendar.MONTH, m-1); // �ڹ� �޷�API�� 1���� 0����, 2���� 1��, ... 12���� 11�� �����Ǿ�����
      firstDay.set(Calendar.DATE, 1); // ex) 2022.04.01	
      int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
      // dayOfWeek    ��1, ��2, ... ��7
      // startBlank    ��0, ��1, ... ��6
      // 1)
      int startBlank = dayOfWeek - 1;
      // ������ ��¥�� �ڹ� �޷�api�� �̿��Ͽ� ������
      // 2)
      int endDay = firstDay.getActualMaximum(Calendar.DATE);// firstDay���� ���� ū���� -> ��������¥
      // strartBlank�� endDay�� ���� ����� endBlank�� ���ؼ� 7�� ����� �ǵ���
      // 3)
      int endBlank = 0;
      if((startBlank+endDay)%7 != 0) {
         // 7���� startBlank+endDay�� 7�� ���� ���������� ����
         endBlank = 7-((startBlank+endDay)%7);
      }
      // 4)
      int totalTd = startBlank + endDay + endBlank;
      
      
      // 2) �𵨰�(���� ����� ����Ʈ)�� ��ȯ�ϴ� �����Ͻ�����(��) ȣ��
      CashbookDao cashbookDao = new CashbookDao();
      List<Map<String, Object>> list = cashbookDao.selectCashbookListByMonth(y, m);
      /*
       �޷���¿� �ʿ��� �𵨰�(1), 2), 3), 4)) + �����ͺ��̽����� ��ȯ�� �𵨰�(list, y��³⵵, m��¿�) + ���ó�¥(today)
       */
      request.setAttribute("startBlank", startBlank);
      request.setAttribute("endDay", endDay);
      request.setAttribute("endBlank", endBlank);
      request.setAttribute("totalTd", totalTd);
      
      request.setAttribute("list", list);
      request.setAttribute("y", y);
      request.setAttribute("m", m);
       
      request.setAttribute("today", today);
      // 3) �� ������
      request.getRequestDispatcher("/WEB-INF/view/CashbookListByMonthView.jsp").forward(request, response);
   }

}