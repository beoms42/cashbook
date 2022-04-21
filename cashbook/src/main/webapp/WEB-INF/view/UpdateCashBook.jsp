<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>

<%
	int cashbookNo = (int)request.getAttribute("cashbookNo");
	int cash = (int)request.getAttribute("cash");
	String kind = (String)request.getAttribute("kind");
	String memo = (String)request.getAttribute("memo");
	
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
		
	 	<form action="UpdateCashbookController" method="post">
	 	<table>
	 		<tr>
	 			<th>번호</th>
	 			<td><input type="number" value="<%=cashbookNo%>" name="cashbookNo" readonly="readonly"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>kind</th>
	 			<td><input type="text" value="<%=kind %>" name="kind"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>memo</th>
	 			<td><input type="text" value="<%=memo %>" name="memo"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>cash</th>
	 			<td><input type="number" name="cash" value="<%=cash %>"></td>
	 		</tr>
	 	</table>
	 	<button type="submit">전송하기</button>
	 	</form>
	 	
	  </div>
	  
	  <div class="col-sm-4 container">
	  </div>
	</div>
</body>
</html>
