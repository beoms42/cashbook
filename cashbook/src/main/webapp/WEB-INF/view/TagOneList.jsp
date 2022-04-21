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
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
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
		
		<h1>tag rank</h1>
		
		<table class="table">
			<tr>
				<th>tag</th>
				<th>cashbookNo</th>
				<th>kind</th>
				<th>memo</th>
				<th>cash</th>
			</tr>
			
			<%
				for(Map<String, Object> map : list) {
			%>
				<tr>
					<td><%=map.get("tag") %></td>
					<td><%=map.get("cashbookNo") %></td>
					<td><%=map.get("kind") %></td>
					<td><%=map.get("memo") %></td>
					<td><%=map.get("cash") %></td>
					
				</tr>
			<%
				}
			%>
		</table>
	  </div>
	  
	  <div class="col-sm-4 container">
	  </div>
	</div>
</body>
</html>
