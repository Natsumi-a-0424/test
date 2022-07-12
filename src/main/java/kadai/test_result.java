package kadai;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test_result
 */
@WebServlet("/test_result")
public class test_result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test_result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//test_result.jspで表示するユーザー名の受け渡し
		String users_name = null;
		users_name = request.getParameter("users_name");
				
		System.out.println(users_name);
				
		request.setAttribute("users_name", users_name);
		
		//問題のidを配列で取得
		String[] id = null;
			
		id = request.getParameterValues("ID[]");
			
		//取得した問題のidをコンソールに出力
		System.out.println(id.length);
			
		//回答フォームに入力されたパラメータを配列で取得
		String[] usersAnswer = null;
		usersAnswer = request.getParameterValues("usersAnswer[]");
			
		//パラメータの取得件数をコンソールに出力
		System.out.println(usersAnswer.length);
			
		//配列分繰り返す
		int count = 0;
		for (int i = 0; i<id.length; i++ ) {
			//問題idと一致するquestions_idをもつanswerを取得のため、ansDaoオブジェクトのインスタンス化
			AnswersDao ansDao = null;
				try {
					ansDao = new AnswersDao();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			
			ArrayList<AnswersBean> ansList = null;
				try {
					//問題idと一致するquestions_idをもつanswerを配列で取得
					ansList = (ArrayList<AnswersBean>) ansDao.find(id[i]);
				} catch (DAOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
				//answerの取得件数をコンソールに出力
				System.out.println("答えの取得件数は" + ansList.size() + "件");
				
				//answerの取得内容を全てコンソールに出力
				//正解数をカウントするint型変数countの用意
				
				for (int j = 0; j < ansList.size(); j++) {
					
					AnswersBean a = ansList.get(j);
	
				    System.out.println("答えは" + a.getAnswer() + "です");
				    
				    //配列型からString型に変換
				    String ua = usersAnswer[i];
				    
				    System.out.println(ua);
				     
				    //取得したanswerと取得したパラメータが一致すれば正解
				    
				    if(ua.equals(a.getAnswer())) {
				    	
				    	count++;
				    	System.out.println(count);
				    	
				    	
				    } 
				    
				}
				
		}
				
			
			
		//id配列の要素数＝全問数
		//正解数(count)÷全問数×100＝得点
		
		//正解数コンソールに出力
		System.out.println(count);
		
		//小数を格納できるdouble型の変数を用意
		double d_score = 0;
		//どちらかの数値をキャスト演算子を使って double 型に変換すると double 型で演算を行った場合の結果を得ることができる
		d_score = count/(double)id.length*100;
		
		int score = 0;
		
		score = (int) Math.round(d_score);
		
		
		System.out.println(score + "点");
		
			
		//問題数、正解数、得点の値をsetAttribute
		request.setAttribute("queNum",id.length);
		request.setAttribute("count",count);
		request.setAttribute("score",score);
		
		//採点時間の取得
		LocalDateTime scoring_time = LocalDateTime.now();
		
		//表示形式の指定
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		String format_time = dtf.format(scoring_time);
		
		System.out.println(format_time);
		
		//採点時間をtext_result.jspへ
		request.setAttribute("scoring_time",format_time);
		
		//HistoryDaoオブジェクトのもつinsertメソッドでテスト結果の記録
		HistoryDao hisDao = null;
		try {
			hisDao = new HistoryDao();
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//insertメソッドの戻り値を格納するint型変数を用意
		int rs = 0;
		try {
			rs = hisDao.insert(users_name, score, format_time);
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		System.out.println(rs);
			
		//test_result.jspに遷移
		RequestDispatcher next =  request.getRequestDispatcher("./test_result.jsp");
	       //フォワードの実行
			try {
				next.forward(request, response);
			} catch (ServletException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		
		
	}

}
