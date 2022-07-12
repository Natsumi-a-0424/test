<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="/kadai/style.css">
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="kadai.QuestionsBean"%>
<%@ page import="kadai.AnswersBean"%>

	<h1>List</h1>

	<div>
	<form action="./top.jsp" style="display: inline"><!-- フォームの遷移先の指定と改行を消去 -->
		<button>top</button><!-- topボタンの作成 -->
	</form>
	
	<form action=""	 method="POST" style="display: inline"><!-- フォームの遷移先とメソッドの指定、改行の消去 -->
		<button type=submit>logout</button><!-- 内容保持したまま送信するsubmit指定、logoutのボタン作成 -->
	</form>
	</div>
	
	<div>
		<form action="./register.jsp" method="POST">	<!-- フォームの遷移先とメソッドの指定、改行の消去 -->
			<button>新規登録</button><!-- 新規登録ボタンの作成 -->
		</form>
	<div>
	
		
	<div>
	<form action="./list" method="POST" style="display: inline"><!-- フォームの遷移先とメソッドの指定 -->
	    
	    <!-- 配列ArrayListのQuestionsBean型変数queListの宣言、ArrayListのQuestionsBean型でlist.javaからのqueListを取得し格納 -->
	    <!-- ansListも同様 -->
		<% 
		ArrayList<QuestionsBean> queList= (ArrayList<QuestionsBean>)request.getAttribute("queList");
		ArrayList<AnswersBean> ansList = (ArrayList<AnswersBean>)request.getAttribute("ansList");
	
		
		for(QuestionsBean queData : queList){ %><!-- 配列queListから要素取り出しQuestionsBean型変数queDataに格納、配列の分だけ繰返し -->
		
			<label for="question">
			問題<input type="text" name="id" value ="<%=queData.getId()%>" style="width:2%" readonly> :<!-- テキストボックスにqueListから取得したidを表示 -->
				<input type="text" name="question" id="question" value ="<%=queData.getQuestion()%>" readonly>	<!-- 同様に取得したQuestionを表示 -->
				
			</label>

	</form>
			
			<form action="./edit" method="POST" style="display: inline"><!-- 遷移先と送信メソッドの指定、改行の消去 -->
			<!-- name="id"のテキストボックスが配列から取得したidを保持した状態で編集画面に遷移 -->
			<input type="hidden" name="ID" value ="<%=queData.getId()%>" >
		 	<button type="submit">編集</button><!-- 編集ボタンの作成 -->
			</form>
			
			<!-- 問題番号を削除画面サーブレット(delete_confirm.java)に渡す -->
			<form action="./delete_confirm" method="POST" style="display: inline"><!-- 遷移先と送信メソッドの指定、改行の消去 -->
			 <!-- このコードを非表示状態に設定、name=idのテキストボックスが配列から取得したidを保持した状態で削除画面に遷移 -->
			<input type="hidden" name="ID" value ="<%=queData.getId()%>" >
			<button type="submit" >削除</button>
			</form>
			
			<br>
			
				<%int i = 1;//int型変数iを1の状態で宣言
				for(AnswersBean ansData : ansList){	%><!-- 配列ansListから要素を取り出しAnswersBean型変数ansDataに格納、配列の分だけ繰返し -->
					<%if(ansData.getQuestions_id() == queData.getId() ){ %><!-- ansDataが取得したquestions_idとqueDataが取得したidが一致 -->
						
						<label>	
						答え<%= i++ %>:<!-- 変数iにプラス１した状態で表示 -->
						<input type="text" name="answer" value ="<%=ansData.getAnswer()%>" readonly><!-- if文がtrueのときansDataが取得したanswerを表示 -->
						</label>	
						
						<br>
				<%} %>
				
				<%}%>
				
				
					
		<%} %>	
	</div>				


	
</body>
</html>