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
 * Servlet implementation class edit_confirm
 */
@WebServlet("/edit_confirm")
public class edit_confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit_confirm() {
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
			System.out.println("ログインIDは" + session.getAttribute("loginId"));
						
			// ログイン中でなかった場合
			if (session.getAttribute("loginId") == null) {
				
				// ログイン画面に遷移
				RequestDispatcher dispacher = request.getRequestDispatcher("./login.jsp");
				dispacher.forward(request,response);
				return ;
			}
			
			//edit.jspで編集された問題と答えのデータをedit_confirm.jspに受け渡し
			
			//edit.jspで編集された問題文とそのidをパラメータとして受け取る
			String questionsId = request.getParameter("questionsId");
			
			System.out.println("編集された問題のidは" + questionsId + "です");
					
			String question = request.getParameter("question");
			
			System.out.println("編集された問題文は" + question + "です");
					
			/*edit.jspで編集された答えのquestions_idと入力された答え文をパラメータとして受け取る
			答え文は複数ある場合に合わせて配列での受け取り*/
			String answersQId = request.getParameter("questions_id");
			
			System.out.println("編集された答えのquestions_idは" + answersQId + "です");
					
			//答え文は複数ある場合に合わせて配列での受け取り
			String[] answer = request.getParameterValues("input[]");
		
			System.out.println("編集された答えは" + answer.length + "件です。");
			
			//取得したquestionと各answerの文字数の確認
			int questionsNumber = question.length();
			System.out.println("問題の文字数は" + questionsNumber);
			
			//答えの文字数を格納する変数の用意
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
	
							request.setAttribute("editQId",questionsId);
							request.setAttribute("editQuestion",question);
							request.setAttribute("editAnswersQId",answersQId);
							request.setAttribute("editAnswer",answer);
							
							//edit_confirm.jspに遷移
							RequestDispatcher dispatcher =  request.getRequestDispatcher("./edit_confirm.jsp");
								//フォワードの実行
									dispatcher.forward(request, response);
							    
							
						}else {
							request.setAttribute("errorMessage","答えが長すぎます(0～200文字)" );
							break;
						}
						
					}
				
				}else {
					
					request.setAttribute("errorMessage","問題が長すぎます(0～500文字)" );
					
				}	
	
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./edit.jsp");
			//フォワードの実行
		    dispatcher.forward(request, response);
			
		} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
		}	
			
	}

}
