<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/kadai/style.css">
<jsp:include page="header.jsp"/>
<title>Edit</title>
</head>
<body>

	<h1>― 編集 ―</h1>
	 <form action="./edit_execution" method="POST" style="display: inline">
		<!-- リクエストスコープからデータを取得 -->
		<% 	String editQId= (String)request.getAttribute("editQId");%>
		<% String editQuestion=(String)request.getAttribute("editQuestion");%>
		<% 	String editAnswersQId= (String)request.getAttribute("editAnswersQId");%>			
	 	<% 	String[] editAnswer= (String[])request.getAttribute("editAnswer");%>
	 	
	 	<%//編集済みの問題IDがとれているか確認 
	 	System.out.println(editQId);%>
	 	
	 	<p><label for="editQId">問題番号:
	 	<input type="text" name="editQId" id="editQId" value="<%=editQId%>" readonly>
	 	</label></p>
	 	
		<p><label for="editQuestion">
			問題:
			<!-- テキストボックスにeditQuestionから取得した問題文questionを表示 -->
			<input type="text" name="editQeustion" id="editQeustion" value="<%=editQuestion%>" readonly>
		</label></p>
	
		<% int i = 1;
		//配列editAnswerの要素分だけ繰返し取得、String型変数aditAに格納
		for(String editA : editAnswer){	%>
			
			<p><label for="editAnswer">
				答え<a class="no"><%= i++ %></a>:<!-- 繰り返した分だけ数字が増える -->
				<input type="text" name="editAnswer[]" id="editAnswer" value="<%=editA%>" readonly>
			</label></p>
			
		<%} %>
				
			
		
		
		
	  <button type="button" onclick="history.back()">戻る</button>
	  	 
	  <input type="hidden" name="editAnswersQId" value ="<%=editAnswersQId%>" >
	  <button type="submit">更新</button> 
	</form>	
	
</body>
</html>