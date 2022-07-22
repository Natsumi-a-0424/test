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
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="kadai.QuestionsBean"%>
<%@ page import="kadai.AnswersBean"%>

	<h1>― 編集 ―</h1>

	<div>
	<form action="./top.jsp" style="display: inline"><!-- top画面に遷移 -->
		 <button>top</button><!-- top画面遷移のボタン -->
	</form>
		
	<form action="./logout.jsp"	 method="POST" style="display: inline"><!-- 送信メソッドPOSTでlogout画面に遷移 -->
		<button type="submit">logout</button><!-- logout遷移ボタン -->
	</form>
	</div>
		
	<br>
	<div>	
	 <form action="./edit_confirm" method="POST" style="display: inline">
	<!-- リクエストスコープからデータを取得 -->
	<% 	QuestionsBean qb= (QuestionsBean)request.getAttribute("editQuestion");%>
	<% ArrayList<AnswersBean> ansList=(ArrayList<AnswersBean>)request.getAttribute("editAnswer");%>			
 	
 	<label for="id">問題番号:
 	 <input type="text" name="ID" id="id" style="width:2%" value ="<%=qb.getId()%>" readonly>
 	</label>
 	
	<label for="question">
		問題:
		<!-- テキストボックスにeditQuestionから取得した問題文questionを表示 -->
		<input type="text" name="question" id="question" value="<%=qb.getQuestion()%>">
	</label>
	
	<br>
	<!-- JavaScriptにて答え記入欄の追加・削除機能を与える -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script type="text/javascript">
		$(function() {
			//追加
			$('.addformbox').click(function() {
				//クローンを変数に格納
				let clonecode = $('.box:last').clone(true);
				
				//数字を＋１し変数に格納
				let cloneno = clonecode.attr('data-formno');
				let cloneno2 = parseInt(cloneno) + 1;
				let cloneno3 = parseInt(cloneno) + 2;
				
				//data属性の数字を＋１
				clonecode.attr('data-formno',cloneno2);
				
				//数値
				clonecode.find('.no').html(cloneno3);
				
				//name属性の数字を+1
				//let namecode = clonecode.find('input.answer').attr('name');
				clonecode.find('input.answer').attr('name','input[]');
		
				
				
				//HTMLに追加
				clonecode.insertAfter($('.box:last'));
			});
			
		
			//削除
			$('.deletformbox').click(function() {
				//クリックされた削除ボタンの親要素を削除
				$(this).parents(".box").remove();
		
				let scount = 0;
				//番号振り直し
				$('.box').each(function(){
					let scount2 = scount + 1;
				
					//data属性の数字
					$(this).attr('data-formno',scount);
					
					$('.no',this).html(scount2);
					
					
					//input番号振り直し
					//let name = $('input.answer',this).attr('name');
					$('input.answer',this).attr('name','input[]');
			
					
		
					scount += 1;
				});
			});	
			
		});
		</script>
	

	<% int i = 1;
	//ansListの要素分だけ繰返し取得、AnswersBean型変数ansDataに格納
	for(AnswersBean ansData : ansList){	%>
	
		 <!-- ansListから取得したquestions_idとqbから取得したidが一致 -->
		<%if(ansData.getQuestions_id() == qb.getId()) { %>
		
		<div class="box" data-formno="0" >
		
		<p><label for="ans">
			答え<a class="no"><%= i++ %></a>:<!-- 繰り返した分だけ数字が増える -->
			<!-- 非表示状態でansListからquestions_idを取得 -->
			<input type="hidden" name="questions_id" value ="<%=ansData.getQuestions_id()%>" >
			<!-- テキストボックスにansListから取得したanswerを表示 -->
			<input type="text" name="input[]" class="answer" value="<%=ansData.getAnswer()%>">
		</label>
		
		<a><button type="button" class="deletformbox">削除</button></a></p>
		
		</div>
		<%} %>
	<%} %>
			
		
	<p><%
		String errorMessage = (String)request.getAttribute("errorMessage");
		
		if (errorMessage != null) {  //errorMessageがnullじゃなかったらerrorMessage表示
		%>
		<span><%= errorMessage %></span><!-- spanタグで前後に改行、errorMessageの表示 -->
		<%
		}
		%></p>
	</div>
	
	<div>
	  <button type="button" onclick="history.back()">戻る</button>
	 
	  <button type="submit">確認</button> 
	  
    <a><button type="button" class="addformbox">答え記入欄追加</button></a>
    </div>
	</form>	

</body>
</html>