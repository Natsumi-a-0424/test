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
<%@ page import="kadai.HistoryBean"%>

<%ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>)request.getAttribute("historyList"); %>

	<h1>― 採点結果　履歴 ―</h1>

	<form action="./logout.jsp" method="POST" style="display: inline">
		 <button>logout</button>
	</form>
	
	 
	
	<form action="./top.jsp" method="POST" style="display: inline">
		 <button>top</button>
	</form>
	
	<p>履歴</p>
	
	<table border="1">
	
		<tr>
			<th>氏名</th>
			<th>得点</th>
			<th>採点時間</th>
		</tr>
		
			
		<%for(HistoryBean historyData : historyList){ %>
		<% String userName = (String)request.getAttribute("userName");%>
			<tr>
				<td><%=userName%></td>
				<td><%=historyData.getPoint() %></td>
				<td><%=historyData.getCreated_at() %></td>
			</tr>
			
		<%} %>
	
	</table>
	


</body>
</html>