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

<h1>― 新規登録 ―</h1>

	<form action="./top.jsp" style="display: inline">
		 <button>top</button>
	</form>
	
 	 <form action="./logout.jsp" method="POST" style="display: inline">
		<button>logout</button>
	</form>
	
	<p>この内容で登録しますか？</p>
	
	<form action="./register" method="POST">
	
	<%String question = request.getParameter("QUESTION"); %>
	
	<%String[] answer = request.getParameterValues("input[]"); %>
		
		
		<label for="question">問題</label>
		<input type="text" name="registerQuestion" id="question" value="<%=question%>" readonly>
		
		<br>
		
		<%int num =1;
		if (answer != null){
		     for (int i = 0 ; i < answer.length ; i++){ %>
		
		<label for="answers">答え<%=num++%>:</label>
		<input type="text" name="registerAnswer" id="answers" value="<%=answer[i]%>"readonly>
		 
		<br>
		
		<%} 
		
		}%>
		
		
		<button type="button" onclick="history.back()">戻る</button>
		
		
		<button type="submit">登録</button>
	</form>
	
</body>
</html>