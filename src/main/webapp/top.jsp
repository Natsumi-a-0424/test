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

	<%String loginId = (String)session.getAttribute("loginId");
	System.out.println("ログインIDは" + session.getAttribute("loginId"));
	
	// ログイン中でなかった場合
	if (session.getAttribute("loginId") == null) {
	  // ログイン画面に遷移
	  RequestDispatcher dispacher = request.getRequestDispatcher("./login.jsp");
	  dispacher.forward(request,response);
	  return ;
	} %>
	
	<div>
	<form action="./list" method="POST" style="display: inline">
		 <button type="submit" class="topPageButton" style="width:270px">問題と答えを確認・登録する</button>
	</form>
	
	<br>
	
	<form action="./test" method="POST" style="display: inline">
		 <button type="submit" class="topPageButton"  style="width:270px">テストする</button>
	</form>
	
	<br>
	
	<form action="./history" method="POST" style="display: inline">
		 <button type="submit" class="topPageButton"  style="width:270px">過去の採点結果を見る</button>
	</form>
	
	</div>
</body>

</html>