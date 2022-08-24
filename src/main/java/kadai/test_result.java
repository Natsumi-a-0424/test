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
import javax.servlet.http.HttpSession;

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
		
		try {
			// セッションを取得
			HttpSession session = request.getSession();
			String loginId = (String)session.getAttribute("loginId");
			System.out.println("ログインIDは" + loginId);
			
			// ログイン中でなかった場合
			if (session.getAttribute("loginId") == null) {
				// ログイン画面に遷移
				RequestDispatcher dispacher = request.getRequestDispatcher("./login.jsp");
				dispacher.forward(request,response);
				return ;
			}
			
			//login_idをString型からint型に変換
			int intLoginId = Integer.parseInt(loginId); 
			
			//test_result.jspで表示するユーザー名をUsersDaoのfindNameメソッドで取得
			UsersDao dao = new UsersDao();
			UsersBean userName = (UsersBean)dao.findName(intLoginId);
			
			System.out.println(userName.getName());
			
			request.setAttribute("userName", userName.getName());
			
			//問題のidを配列で取得
			String[] questionsId = request.getParameterValues("ID[]");
				
			//取得した問題idの件数をコンソールに出力
			System.out.println(questionsId.length);
				
			//回答フォームに入力されたパラメータを配列で取得
			String[] usersAnswer = request.getParameterValues("usersAnswer[]");
				
			//パラメータの取得件数をコンソールに出力
			System.out.println(usersAnswer.length);
				
			//配列分繰り返す
			int count = 0;
			for (int i = 0; i<questionsId.length; i++ ) {
				//問題idと一致するquestions_idをもつanswerを取得のため、ansDaoオブジェクトのインスタンス化
				AnswersDao answersDao =  new AnswersDao();
				
				//問題idと一致するquestions_idをもつanswerを配列で取得
				ArrayList<AnswersBean> answersList = (ArrayList<AnswersBean>) answersDao.find(questionsId[i]);
		
				//answerの取得件数をコンソールに出力
				System.out.println("答えの取得件数は" + answersList.size() + "件");
					
				//answerの取得内容を全てコンソールに出力
				//正解数をカウントするint型変数countの用意
					
					for (int j = 0; j < answersList.size(); j++) {
						
						AnswersBean a = answersList.get(j);
		
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
			double dbPoint = 0;
			//どちらかの数値をキャスト演算子を使って double 型に変換すると double 型で演算を行った場合の結果を得ることができる
			dbPoint = count/(double)questionsId.length*100;
			
			int point = (int) Math.round(dbPoint);
			
			
			System.out.println(point + "点");
			
				
			//問題数、正解数、得点の値をsetAttribute
			request.setAttribute("questionsNumber",questionsId.length);//問題数
			request.setAttribute("count",count);//正解数
			request.setAttribute("point",point);//得点
			
			//採点時間の取得
			LocalDateTime time = LocalDateTime.now();
			
			//表示形式の指定
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			
			String formatTime = dtf.format(time);
			
			System.out.println(formatTime);
			
			//採点時間をtext_result.jspへ
			request.setAttribute("time",formatTime);
			
			
			//HistoryDaoオブジェクトのもつinsertメソッドでテスト結果の記録
			
			HistoryDao hisDao = new HistoryDao();
	
			//insertメソッドの戻り値を格納するint型変数を用意
			int rs = hisDao.insert(intLoginId, point, formatTime);
				
			System.out.println(rs);
				
			//test_result.jspに遷移
			RequestDispatcher next =  request.getRequestDispatcher("./test_result.jsp");
			//フォワードの実行
			next.forward(request, response);
				
		} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
		}
		
	}

}
