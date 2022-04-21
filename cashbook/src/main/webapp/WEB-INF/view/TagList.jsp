<%@page import="java.text.SimpleDateFormat"%>
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

<% 
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");

	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
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
	  <!--  grid 5의 시작  -->
		<form method="post" action="<%=request.getContextPath()%>/TagSearchController">
         <div class="float-right">
            <select class="text-center" name="kind">
               <option value="수입">수입</option>
               <option value="지출">지출</option>
            </select>
            <input type="date" name="beginDate"> ~ <input type="date" name="endDate" value=<%=sf.format(nowTime)%>>
            <button type="submit">검색</button>
         </div>
      </form>
      
		<h1>tag rank</h1>
		
		<table class="table">
			<tr>
				<th>rank</th>
				<th>tag</th>
				<th>count</th>
			</tr>
			
			<%
				for(Map<String, Object> map : list) {
			%>
				<tr>
					<td><%=map.get("rank") %></td>
					<td><a href="<%=request.getContextPath()%>/TagOneController?tag=<%=map.get("tag")%>" ><%=map.get("tag")%></a></td>
					<td><%=map.get("cnt") %></td>
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
