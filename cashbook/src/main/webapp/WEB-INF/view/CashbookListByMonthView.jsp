<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
	  <h1>가계부</h1>
	</div>

	<br>
	 <br>
	 <br>
	
	<div class="row">
	
	  <div class="container col-sm-1">
	  </div>
	  <div class="container col-sm-1">
		 		<jsp:include page="/inc/leftMenu.jsp"></jsp:include>
	  </div>
	  <div class="container col-sm-1">
	  </div>
	  
	  <div class="col-sm-7 container">
			<%
        List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
        int y = (Integer)request.getAttribute("y");
        int m = (Integer)request.getAttribute("m");
        int today = (Integer)request.getAttribute("today");
        int startBlank = (Integer)request.getAttribute("startBlank");
        int endBlank = (Integer)request.getAttribute("endBlank");
        int endDay = (Integer)request.getAttribute("endDay");
        int totalTd = (Integer)request.getAttribute("totalTd");


    %>
    <div>
    	<%= session.getAttribute("sessionMemberId")%>님 반갑습니다.
    	<a href="<%=request.getContextPath()%>/LoginController">로그아웃</a>
    </div>
    
    <div>
    	<a href= "<%=request.getContextPath()%>/TagController">태그랭킹</a>
    </div>
    <h2><%=y %>년<%=m %>월</h2>

    <div>
        <a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y %>&m=<%=m-1 %>">이전month</a>
        <a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y %>&m=<%=m+1 %>">다음month</a>
    </div>
    <!-- 
        1) 이번달 1일의 요일 firstDayYoil
        2) 요일 -> startBlank -> 일 0, 월 1, 화2, ... 토6
        3) 이번달 마지막날짜
        4) endBlank -> totalBlank
        5) td의 갯수 1 ~ totalBlank
            +
        6) 가계부 + list
     -->
     
     <table class="table table-bordered table-striped" >
     		<thead>
     			<tr>
     				<th>일</th>
     				<th>월</th>
     				<th>화</th>
     				<th>수</th>
     				<th>목</th>
     				<th>금</th>
     				<th>토</th>
     			</tr>
     		</thead>
	     	<tbody>
		     	<tr>
		     				<%
		     			for(int i = 1; i<= totalTd; i++) {
		     					if(i-startBlank > 0 && i-startBlank <= endDay) {
		     						String c = "";
		     						if(i%7==0) {
		     							c = "text-primary";
		     						} else if(i%7==1) {
		     							c = "text-danger";
		     						}
		     						%>
		         					<td class="<%=c%>">
		         						<%=i-startBlank %>
		         						<a href="<%=request.getContextPath()%>/insertCashbookController?y=<%=y%>&m=<%=m%>&d=<%=i-startBlank%>" class="btn btn-light">입력</a>
		         						<!-- 해당 날짜의 cashbook list를 출력 -->
		         						
		         						<%
		         						for(Map map3 : list) {
		         							if((int)map3.get("day") == (i-startBlank)) {
		         								%>
		         								<div>
		         									<a href="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=map3.get("cashbookNo")%>">
				         								[<%=map3.get("kind")%>]
				         								<%=map3.get("cash")%>원
				         								<%=map3.get("memo")%>
			         								</a>
		         								</div>
		         								<%
		         							}
		         						}
		         						%>
		         						
		         					</td>
		         					<%
		     					} else {
		     						%>
		         					<td>&nbsp;</td>
		         					<%
		     					}
		     						
		     				
		     					
		     				if(i<=totalTd && i%7 == 0) {
		     					%>
		     					</tr><tr>
		     					<%
		     				}
		     			}
		     		%>
		     	</tr>
	     	</tbody>
     </table>
	  </div>
	  
	  <div class="col-sm-2 container">
	  </div>
	</div>
</body>
</html>
