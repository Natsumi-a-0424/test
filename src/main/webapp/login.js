$(function() {
	$("#ID").on('input',function(){//input id="ID"のテキストボックスに入力されたら
		let input_text = $(this).val();//入力値をinput_textに格納
		console.log(input_text);//入力値をコンソールに出力
		
		$.ajax({
			type: "POST",
			url: "./login_ajax",
			data:{"ID":input_text},
			dataType: "JSON",
			cache: false,
			success: function(data){//成功したときdataにサーバーから返されたhtmlが入る
			console.log(data);
				if(data !== 0) {//返された結果が0じゃなければ
					$('.result_text').text("一致するユーザーが存在します");
				}else{
					$('.result_text').text("一致するユーザーが存在しません");
				}
			},
			error: function(){
				
			}
		});
	});
});
   