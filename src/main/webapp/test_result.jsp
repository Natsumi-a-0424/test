<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/kadai/style.css">
<jsp:include page="header.jsp"/>
<title>Insert title here</title>
</head>
<body>
	<%String usersName = (String)request.getAttribute("userName"); %>
	<%int questionsNumber = (int)request.getAttribute("questionsNumber"); %>
	<%int correct = (int)request.getAttribute("count"); %>
	<%int score = (int)request.getAttribute("point"); %>
	<%String time = (String)request.getAttribute("time"); %>
	
	<h1>― テスト採点結果 ―</h1>
	
	<form action="./top.jsp" style="display: inline"><!-- フォームの遷移先の指定と改行を消去 -->
		<button type="submit">top</button><!-- topボタンの作成 -->
	</form>
	
	<form action=""	 method="POST" style="display: inline"><!-- フォームの遷移先とメソッドの指定、改行の消去 -->
		<button type=submit>logout</button><!-- 内容保持したまま送信するsubmit指定、logoutのボタン作成 -->
	</form>

	
	<p><%=usersName %>さん</p>
	
	<!-- 問題数と正解数 -->
	<p><%=questionsNumber %>問中<%=correct %>問正解です。</p>
	
	<!-- 正解数÷問題数で点数表示。小数点以下四捨五入 -->
	<p><%=score %>点でした。</p>
	
	<!-- 現在時刻の表示 -->
	<p><%=time %></p>
	
	
</body>
</html>