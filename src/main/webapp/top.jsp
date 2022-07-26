<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<jsp:include page="header.jsp"/>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/kadai/style.css">
<title>Top</title>
</head>
<body>

	<h1>Top</h1>

<!-- test_result.jspでユーザー名表示のために非表示でユーザー名を渡す -->
	<%String users_name = (String)request.getAttribute("users_name"); %>
	<%System.out.println(users_name); %>
	
	<div>
	<form action="./logout.jsp" method="POST" style="display: inline">
		 <button type="submit"class="logoutButton" >logout</button>
	</form>
	</div>
	
	<br>
	
	<div>
	<form action="./list" method="POST" style="display: inline">
		 <button type="submit" class="topPageButton" style="width:270px">問題と答えを確認・登録する</button>
	</form>
	
	<br>
	
	<form action="./test" method="POST" style="display: inline">
	
	
	<input type="hidden" name="users_name" value ="<%=users_name%>" >
		 <button type="submit" class="topPageButton"  style="width:270px">テストする</button>
	</form>
	<br>
	<form action="./history" method="POST" style="display: inline">
	<input type="hidden" name="users_name" value ="<%=users_name%>" >
		 <button type="submit" class="topPageButton"  style="width:270px">過去の採点結果を見る</button>
	</form>
	</div>
</body>

</html>