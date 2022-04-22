<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
					

			<form action="<%=request.getContextPath()%>/InsertMemberController" method="post">
				<table class="table">	
					<tr>
						<td>memberId :</td> 
						<td><input type="text" name="memberId"></td>
					</tr>
					
					<tr>
						<td>memberPw :</td> 
						<td><input type="password" name="memberPw"></td>
					</tr>
				</table>
			<button type="submit" class="button">회원가입</button> 
			</form>
	  </div>
	  
	  <div class="col-sm-4 container">
	  </div>
	</div>
</body>
</html>
