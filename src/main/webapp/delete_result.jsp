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

	<h1>― 削除 ―</h1>
データを削除しました。

	<form action="./list" method="POST">
		 <button>問題一覧に戻る</button>
	</form>

	<form action="./top.jsp">
			 <button>top</button>
	</form>


</body>
</html>