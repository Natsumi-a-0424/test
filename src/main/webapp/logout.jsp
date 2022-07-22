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

<h1>― Logout ―</h1>

<p>ログアウトしますか</p>

<form action="./logout" method="POST">
<button type=submit>logout</button>
</form>

<button type="button" onclick="history.back()">戻る</button>

 


</body>
</html>