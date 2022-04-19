<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<%
	
	int[] today = (int[])request.getAttribute("today"); // 0�� year, 1�� month, 2�� day
	String temp_month = "";
	String temp_day = "";
	if(today[1] < 10) {
		temp_month = "0" + today[1];
	}
	
	if(today[2] < 10) {
		temp_day = "0" + today[2];
	}
	String today_s = today[0] +"-"+ temp_month +"-"+  temp_day;
	
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
	  <h1>�����</h1>
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
	  
	  <form method="post" action="insertCashbookController">
	  <!-- �Ѵ� �ʿ������� �𸣴ϱ� -->
		  <input type="date" value="<%=today_s%>" readonly="readonly" name = "today_s">
		  <input type="number" name="y" value="<%=today[0]%>" hidden="hidden">
		  <input type="number" name="m" value="<%=today[1]%>" hidden="hidden">
		  <input type="number" name="d" value="<%=today[2]%>" hidden="hidden">
		  <hr>
		  
		  <select name="kind">
			<option value="����">����</option>
			<option value="����">����</option>
		  </select>
		  
		  price :<input type="number" name="cash"> 
		  <hr>
		  �޸� : <input type="text" name="memo"> 
		  <button type="submit">����</button>
	  </form>
	  <!--  grid 5������ �� div -->
	  </div>
	  
	  <div class="col-sm-4 container">
	  </div>
	</div>
</body>
</html>
