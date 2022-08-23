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
 * Servlet implementation class edit
 */
@WebServlet("/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit() {
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
		
		/*流れ
		 * list.jspから選択された問題のidをパラメータとして取得
		 * 取得した問題のidをキーにして問題文を取得
		 * 同様に問題のidをキーにして対応する答えを取得する
		 * 取得した問題文と答えをedit_confirm.jspに渡す*/
		
		
		
		//list.jspから編集する問題のidを取得する
		//取得するパラメータ(問題のid)を格納する変数の宣言(getParameterの戻り値はString型)
		String questionsId = request.getParameter("questionsId");
		
		//取得したパラメータをコンソールに出力して確認
		System.out.println("編集する問題のidは" + questionsId + "です");
		
		
		try {
			//取得した問題のidをキーにして、付随するレコード(question)のを取得
			//QuestionsDaoオブジェクトのfindメソッドを利用
			//QuestionsDaoオブジェクトのインスタンス化
			QuestionsDao questionsDao = new QuestionsDao();
			
			//queDaoオブジェクト(QuestionsDaoクラス)がもつfindメソッドを呼び出し、questionを取得
			//findメソッドの戻り値を格納するQuestionsBean型の変数を用意
			//queDaoオブジェクト(QuestionsDaoクラス)がもつfindメソッドを呼び出し、QeustionsBean型で戻り値を格納
			//questionsIdをキーにして付随するquestionをfindして格納
			QuestionsBean questionsBean =(QuestionsBean)questionsDao.find(questionsId);
				
			//qbが取得できたquestionをコンソールに出力して確認
			System.out.println("編集する問題は" + questionsBean.getQuestion() + "です");
		
			//問題のidをキーにしてquestions_idが一致する答えを取得する(答えが複数の場合もあるので配列を使用)
			//AnswersDaoオブジェクトのインスタンス化
			AnswersDao answersDao = new AnswersDao();
		
			//配列ArrayList<AnswersBean>型の変数を用意し、ansDaoオブジェクト(AnswersDaoクラス)のもつfindメソッドの戻り値をArrayList<AnswersBean>型で格納
			ArrayList<AnswersBean> answersList = (ArrayList<AnswersBean>)answersDao.find(questionsId);//findメソッドにgetParameterしたidを持たせてる
			
			//取得したanswerの件数をコンソールに出力して確認
			System.out.println("編集する答えの件数は" + answersList.size() + "件です");
			
			//取得した問題文questionと答えanswerをsetAttributeしてedit_confirm.jspに渡す
			
			request.setAttribute("editQuestion",questionsBean);//questionsBeanの中身はidとquestion
			
			request.setAttribute("editAnswer",answersList);//ansListの中身は答えのid,questions_id,answer
			
			//edit.jspに遷移
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./edit.jsp");
	        //フォワードの実行
	        
				dispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}

}
