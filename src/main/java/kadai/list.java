
package kadai;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		
	try {
		//getParameterメソッド使用してデータ取得、取得した値をString型の変数に代入
		String id = request.getParameter("ID");
		String question = request.getParameter("QUESTION");
		String answerId = request.getParameter("ANSID");
		String questionId = request.getParameter("QUEID");
		String answer = request.getParameter("ANSWER");

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
		
		
		QuestionsDao questionsDao = new QuestionsDao();
		//#ArrayList(配列)としてQuestionsBean型のqueListを作り、そこにqueDaoオブジェクト(QuestionsDaoクラス)が持つfindAllメソッドを実行した戻り値を格納
		ArrayList<QuestionsBean> questionsList = (ArrayList<QuestionsBean>) questionsDao.findAll();//queDaoオブジェクトがもつfindAllメソッドの戻り値を配列として格納
			
		//# 取得できたレコードのquestionsの件数をコンソールに出力して確認
		System.out.println("取得したquestionsの件数は"+ questionsList.size() + "件です");
		
		//AnswersDao型のオブジェクト「ansDao」を用意
		AnswersDao answersDao = new AnswersDao();//オブジェクトの作成
			
		//#ArrayList(配列)としてAnswersBean型のansListを作り、そこにansDaoオブジェクト(AnswersDaoクラス)が持つfindAllメソッドを実行した戻り値を格納
		ArrayList<AnswersBean> answersList = (ArrayList<AnswersBean>) answersDao.findAll();//ansDaoオブジェクトがもつfindAllメソッドの戻り値を配列として格納
				

		//# 取得できたレコードのcorrect_answersの件数をコンソールに出力して確認
		System.out.println("取得したanswerの件数は" + answersList.size() + "件です");

		request.setAttribute("questionsList",questionsList );//queDaoオブジェクトfindAllメソッドの戻り値の配列をsetAttribute
				
		request.setAttribute("answersList",answersList );//ansDaoオブジェクトfindAllメソッドの戻り値の配列をsetAttribute
				
				
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./list.jsp");//list.jspに遷移
		//フォワードの実行
		dispatcher.forward(request, response);
				
				
	} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		// TODO 自動生成された catch ブロック
	}
}


