<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
	 	 <div>
	    	<%= session.getAttribute("sessionMemberId")%>님 반갑습니다.
	    	<a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
	    </div>
		<h1>tag rank</h1>
		<% 
			List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
		%>
		<table class="table">
			<tr>
				<th>kind</th>
				<th>cashDate</th>
				<th>tag</th>
			</tr>
			
			<%
				for(Map<String, Object> map : list) {
			%>
				<tr>
					<td><%=map.get("kind") %></td>
					<td><%=map.get("cashDate") %></td>
					<td><%=map.get("tag") %></td>
					
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
