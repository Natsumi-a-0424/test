package kadai;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//register_confirm.jspから入力された問題文を受け取る
		String question = null;
		question = request.getParameter("rgstQuestion");
		
		//問題文が受け取れているかコンソールに出力して確認
		System.out.println(question);
		
		//取得した問題文の文字数を表示
		int queNum =question.length();
		System.out.println("問題の文字数は" + queNum);
		
		//register_confirm.jspから入力された答えの配列を受け取る
		String[] answer = null;
		answer = request.getParameterValues("rgstAnswer");
		
		int ansNum = 0;
		
		if (answer != null){
		     for (int i = 0 ; i < answer.length ; i++){
		    	//受け取った配列の中身をコンソールに出力して確認
		        System.out.println(answer[i]);
		        
		        ansNum = answer[i].length();
		        System.out.println("答えの文字数は" + ansNum);
		        
		     }
		 }  
		
		
		
		//問題の文字数が500文字を超えていた場合エラーメッセージの表示
		if(queNum < 501) {
			
			for(int j = 0;  j <answer.length ; j++) {
				
				//答えの文字数が200文字を超えていた場合エラーメッセージの表示
				if(ansNum < 201) {	
						
						
					//QuestionsDao型ののオブジェクト「queDao」を用意
					QuestionsDao queDao = null;
					try {
						queDao = new QuestionsDao();
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					
					//queDaoオブジェクト(QuestionsDaoクラス)が持つinsertメソッドを実行した戻り値を格納するためのint型変数の用意
					int rgstId = 0;//IDの最大値が返ってくるのでint型
					try {
						rgstId  = queDao.insert(question);//questionsDaoオブジェクトのもつinsertメソッドの戻り値(取得したidの最大値)格納、insertメソッドにはquestionを渡す
						
						AnswersDao ansDao = new AnswersDao();//AnswersDaoオブジェクトの持つinsertメソッド使用するために、AnswersDaoオブジェクトのインスタンス化
						 ansDao.insert(rgstId, answer);//insertメソッドの実行。insertメソッドには問題文登録時に取得したidの最大値rgstIDとanswerを渡す
					} catch (Exception e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					} 
					
					//QuestionsDaoからの戻り値をコンソールの出力して確認
					System.out.println(rgstId);//問題文のidと答えのquestions_idが何になったか
					
					RequestDispatcher dispatcher =  request.getRequestDispatcher("./list");//登録後問題一覧画面に遷移する
					//フォワードの実行
			        try {
						dispatcher.forward(request, response);
					} catch (ServletException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
				
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
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
	}
}

