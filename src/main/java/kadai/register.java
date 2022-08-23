package kadai;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
			HttpSession session = request.getSession(false);
			System.out.println("ログインIDは" + session.getAttribute("loginId"));
											
			// ログイン中でなかった場合
			if (session.getAttribute("loginId") == null) {
									
				// ログイン画面に遷移
				RequestDispatcher dispacher = request.getRequestDispatcher("./login.jsp");
				dispacher.forward(request,response);
				return ;
			}
			
			//register_confirm.jspから入力された問題文を受け取る
			String question = request.getParameter("registerQuestion");
			
			//問題文が受け取れているかコンソールに出力して確認
			System.out.println(question);
			
			//取得した問題文の文字数を表示
			int questionsNumber =question.length();
			System.out.println("問題の文字数は" + questionsNumber);
			
			//register_confirm.jspから入力された答えの配列を受け取る
			String[] answer = request.getParameterValues("registerAnswer");
			
			int answersNumber = 0;
			
			if (answer != null){
			     for (int i = 0 ; i < answer.length ; i++){
			    	//受け取った配列の中身をコンソールに出力して確認
			        System.out.println(answer[i]);
			        
			        answersNumber = answer[i].length();
			        System.out.println("答えの文字数は" + answersNumber);
			        
			     }
			 }  
			
			
			
			//問題の文字数が500文字を超えていた場合エラーメッセージの表示
			if(questionsNumber < 501) {
				
				for(int j = 0;  j <answer.length ; j++) {
					
					//答えの文字数が200文字を超えていた場合エラーメッセージの表示
					if(answersNumber < 201) {	
							
							
						//QuestionsDao型ののオブジェクト「queDao」を用意
						QuestionsDao questionsDao = new QuestionsDao();
						
						//セッションの登録フラグの判定
						if  (session.getAttribute("registerFlag") == null) {	
							
						
						
							//queDaoオブジェクト(QuestionsDaoクラス)が持つinsertメソッドを実行した戻り値を格納するためのint型変数の用意
							int registerId = questionsDao.insertAndGetId(question);//IDの最大値が返ってくるのでint型
							//questionsDaoオブジェクトのもつinsertメソッドの戻り値(取得したidの最大値)格納、insertメソッドにはquestionを渡す
								
							AnswersDao answersDao = new AnswersDao();//AnswersDaoオブジェクトの持つinsertメソッド使用するために、AnswersDaoオブジェクトのインスタンス化
							answersDao.insert(registerId, answer);//insertメソッドの実行。insertメソッドには問題文登録時に取得したidの最大値rgstIDとanswerを渡す
							
							//QuestionsDaoからの戻り値をコンソールの出力して確認
							System.out.println(registerId);//問題文のidと答えのquestions_idが何になったか
							
							//登録成功フラグを立てる
							session.setAttribute("registerFlag", "OK");
						}	
							
						RequestDispatcher dispatcher =  request.getRequestDispatcher("./list");//登録後問題一覧画面に遷移する
						//フォワードの実行
					    dispatcher.forward(request, response);
						return;
							
						
					}else {
						
						request.setAttribute("errorMessage","答えが長すぎます(0～200文字)" );
						break;
					}
				
				}	
				
			}else {
				
				request.setAttribute("errorMessage","問題が長すぎます(0～500文字)" );
				
			}	
			
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./register.jsp");
	        //フォワードの実行
	    	dispatcher.forward(request, response);
			
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
	}
}

