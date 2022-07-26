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
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="kadai.QuestionsBean"%>
<%@ page import="kadai.AnswersBean"%>

	<h1>― テスト ―</h1>

	<form action="./top.jsp" style="display: inline"><!-- フォームの遷移先の指定と改行を消去 -->
		<button>top</button><!-- topボタンの作成 -->
	</form>
	
	<form action=""	 method="POST" style="display: inline"><!-- フォームの遷移先とメソッドの指定、改行の消去 -->
		<button type=submit>logout</button><!-- 内容保持したまま送信するsubmit指定、logoutのボタン作成 -->
	</form>
	
	<form action="./test_result" method="POST" ><!-- 遷移先test_result.javaでPOSTメソッドで送信 -->
		<!-- 問題文の配列を取得 -->
		<%ArrayList<QuestionsBean> queList= (ArrayList<QuestionsBean>)request.getAttribute("queList"); %>
		
		<!-- 連番の付与と配列分繰返し表示 -->
		<%int i = 1;
		for(QuestionsBean queData : queList){ %>
		
		<p><label for="question">
			<!-- 問題の連番付与 -->
			<%= i++ %>
			<!-- 問題文テキストボックスは入力不可 -->
			<input type="hidden" name="ID[]" value="<%=queData.getId()%>">
			<input type="text" id="question[]" value="<%=queData.getQuestion()%>" readonly>
			
			<%System.out.println(queData.getId()); %>
			<%System.out.println(queData.getQuestion()); %>
		</label></p>
	
		<p><Label for="answer">
			回答
			<!-- 入力可能なテキストボックス -->
			<input type="text" id="answer[]" name="usersAnswer[]">
		
		</Label></p>
		
		<%} %>
		
		<!-- test_result.jspで表示するユーザー名を非表示で渡す -->
		<%String users_name = (String)request.getAttribute("users_name"); %>
		<%System.out.println(users_name); %>
		<input type="hidden" name="users_name" value ="<%=users_name%>" >
		<!-- 内容を保持してtest_result.javaに遷移 -->
		<button type="submit">採点</button>
	</form>


</body>
</html>