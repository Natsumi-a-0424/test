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
	 
	<form action="./top.jsp" style="display: inline">
		 <button>top</button>
	</form>
	
 	 <form action="./logout.jsp" method="POST" style="display: inline">
		<button>logout</button>
	</form>
	
	
	
	<form action="./register_confirm.jsp" method="POST" style="display: inline">
		
		<p><label for="que">問題:</label>
	    <input type="text" name="QUESTION" id="que"></p>
		  
 	
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


	<div class="box" data-formno="0" >
		
		<p><label for="ans">答え<a class="no">1</a>:</label>
		<input type="text" name="input[]" class="answer">
	
		<a><button type="button" class="deletformbox">削除</button></a></p>
	</div>
	   	
	   	<p><%
		String errorMessage = (String)request.getAttribute("errorMessage");
		
		if (errorMessage != null) {  //errorMessageがnullじゃなかったらerrorMessage表示
		%>
		<span><%= errorMessage %></span><!-- spanタグで前後に改行、errorMessageの表示 -->
		<%
		}
		%></p>
	   		   	
	   	<button type="button" onclick="history.back()">戻る</button>
	   	<button type="submit">確認</button> <!-- 確認ボタンを押すと入力された問題文と答えがregister.javaに送られる -->
	   	<a><button type="button" class="addformbox">追加</button></a>
	
</form>	   	
	   
	
		 	
	   	
</body>
</html>