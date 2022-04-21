	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>

<%
	
	Cashbook list = (Cashbook)request.getAttribute("list");
	
%>
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
	  
	  <div class="col-sm-5 container">
		
	 	
	 	<table>
	 		<tr>
	 			<th>번호</th>
	 			<td><%=list.getCashbookNo() %></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>kind</th>
	 			<td><%=list.getKind() %></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>memo</th>
	 			<td><%=list.getMemo() %></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>cash</th>
	 			<td><%=list.getCash() %></td>
	 		</tr>
	 	</table>
	 	<a href="UpdateCashbookController?cashbookNo=<%=list.getCashbookNo()%>&kind=<%=list.getKind() %>&memo=<%=list.getMemo() %>&cash=<%=list.getCash() %>">[수정]</a><a href="DeleteCashbookController?cashbookNo=<%=list.getCashbookNo()%>">[삭제]</a>
	  </div>
	  
	  <div class="col-sm-4 container">
	  </div>
	</div>
</body>
</html>
