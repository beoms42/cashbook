<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		int y = (Integer)request.getAttribute("y");
		int m = (Integer)request.getAttribute("m");
	%>

<div class="jumbotron text-center" style="margin-bottom:0">
	  <h1>SAKILA DB</h1>
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
	  
	  <div class="col-sm-5 container">
	  <h2><%=y%>년 <%=m%>월</h2>
		<table border ="1">
			<tr>
				<th>day</th>
				<th>kind</th>
				<th>cash</th>
			</tr>
			<%
			List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("list");
			for(Map map : list) {
			%>
				<tr>
					<td><%=map.get("day") %></td>
					<td><%=map.get("kind") %></td>
					<td><%=map.get("cash") %></td>
				</tr>
			
			<%
			}
			%>
		</table>
		<div>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m-1%>">이전</a>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m+1%>">다음</a>
	</div>

	  </div>
	  
	  <div class="col-sm-4 container">
	  </div>
	</div>
</body>
</html>
