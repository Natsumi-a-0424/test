<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="/kadai/style.css">
	
	<title>Login</title>
</head>
<body>

	<h1>Login</h1>
	
	
	 <form action="./Login" method="POST"><!--送信ボタンをした時の送り先をLogin.java、転送方法をPOSTメソッドに指定 -->
		 
		 <p>
			 <label for="ID">ID:</label><!-- テキストボックスにID:のラベル付け -->
			 <input type="text" name="ID" id="ID"><!-- 名前IDのテキストボックス作成 -->
		 </p>
		 
		 <p>
			 <label for="pw">pw:</label><!-- テキストボックスにpw:のラベル付け -->
			 <input type="text" name="PW" id="pw"><!-- 名前PWのテキストボックス作成 -->
		 </p>
		 
			 <%
			String errorMessage = (String)request.getAttribute("errorMessage");
			//Login.javaでsetAttributeしたerrorMessageをString型で取得してString型変数errorMessageに格納
			
			if (errorMessage != null) {  //errorMessageがnullじゃなかったらerrorMessage表示
			%>
			<p style="text-align:  center; font-size:90%; line-height:0%">
			<%= errorMessage %>
			</p><!-- spanタグで前後に改行、errorMessageの表示 -->
			<%
			}
			%>
		 
		 <button class="logButton" type="submit" style="margin-top:3%" >login</button><!-- submitで値を保持したまま送信 -->
		 
	 </form>


	
	
</body>
</html>
