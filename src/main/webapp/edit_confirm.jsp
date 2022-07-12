<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/kadai/style.css">
<title>Insert title here</title>
</head>
<body>
	 <form action="./edit_execution" method="POST" style="display: inline">
		<!-- リクエストスコープからデータを取得 -->
		<% 	String edit_id= (String)request.getAttribute("edit_id");%>
		<% String edit_question=(String)request.getAttribute("edit_question");%>
		<% 	String edit_questionsId= (String)request.getAttribute("edit_questionsId");%>			
	 	<% 	String[] edit_answer= (String[])request.getAttribute("edit_answer");%>
	 	
	 	<%//edit_questionsIdがとれているか確認 
	 	System.out.println(edit_questionsId);%>
	 	
	 	<p><label for="editId">問題番号:
	 	<input type="text" name="id" id="editId" value="<%=edit_id%>" readonly>
	 	</label></p>
	 	
		<p><label for="editQuestion">
			問題:
			<!-- テキストボックスにeditQuestionから取得した問題文questionを表示 -->
			<input type="text" name="question" id="editQeustion" value="<%=edit_question%>" readonly>
		</label></p>
	
		<% int i = 1;
		//配列edit_answerの要素分だけ繰返し取得、String型変数aditAに格納
		for(String editA : edit_answer){	%>
			
			<p><label for="editAnswer">
				答え<a class="no"><%= i++ %></a>:<!-- 繰り返した分だけ数字が増える -->
				<input type="text" name="editAnswer[]" id="editAnswer" value="<%=editA%>" readonly>
			</label></p>
			
		<%} %>
				
			
		
		
		
	  <button type="button" onclick="history.back()">戻る</button>
	  	 
	  <input type="hidden" name="edit_questionsId" value ="<%=edit_questionsId%>" >
	  <button type="submit">更新</button> 
	</form>	
	
</body>
</html>