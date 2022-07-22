<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/kadai/style.css">
<jsp:include page="header.jsp"/>
<title>Delete</title>
</head>
<body>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="kadai.QuestionsBean"%>
<%@ page import="kadai.AnswersBean"%>
<%@ page import="kadai.delete_confirm" %>

	<h1>― 削除 ―</h1>

	<form action="./top.jsp" style="display: inline">
		 <button>top</button>
	</form>
		
	<form action="./logout.jsp"	 method="POST" style="display: inline">
		<button>logout</button>
	</form>
		
		<br>
	<!-- リクエストスコープからデータを取得 -->
	<% 	QuestionsBean qb= (QuestionsBean)request.getAttribute("qb");%>
	<% ArrayList<AnswersBean> ansList=(ArrayList<AnswersBean>)request.getAttribute("ansList");%>			
 	
 	
	<label for="question">
		問題:
		<input type="text" name="question" id="question" value="<%=qb.getQuestion()%>"readonly>
	</label>
	
	<br>

	<% int i = 1;
	for(AnswersBean ansData : ansList){	%>
	
		 
		<%if(ansData.getQuestions_id() == qb.getId()) { %>
		
		<label for="answer">
			答え<%= i++ %>:
			<input type="text" name="answer" id="answer" value="<%=ansData.getAnswer()%>"readonly>
		</label>
		
		<br>
		<%} %>
	
	<%} %>
	
	
  <button type="button" onclick="history.back()">戻る</button>
  
  <form action="./delete_final_confirm" method="POST" style="display: inline">
  <input type="hidden" name="ID" value ="<%=qb.getId()%>" >
  <button>削除</button> 
	</form>	

</body>
</html>