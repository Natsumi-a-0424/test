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
 * Servlet implementation class delete_confirm
 */
@WebServlet("/delete_confirm")
public class delete_confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_confirm() {
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
		
			//getParameterメソッド使用してlist.jspから削除する問題番号(id)を取得、取得した値をString型の変数idに代入
			String questionsId = request.getParameter("questionsId");
					
			//list.jspから受け取った問題のidをコンソールに表示
			System.out.println(questionsId);
					
	         request.setAttribute("questionsId",questionsId);	
					
					
			//#QuestionsDao型のオブジェクト、「queDao」を用意
			QuestionsDao questionsDao = new QuestionsDao();
					
			//QuestionsBean型のqbを作り、そこにqueDaoオブジェクト(QuestionsDaoクラス)が持つfindメソッドを実行した戻り値を格納
			//パラメータを格納したidを引数としてqueDaoオブジェクトがもつfindメソッドに渡して、その実行結果を格納
			QuestionsBean questionsBean =  (QuestionsBean)questionsDao.find(questionsId);
					
			//# 取得できたレコードのquestionをコンソールに出力して確認
			System.out.println(questionsBean.getQuestion());
						
			//AnswersDao型のオブジェクト「ansDao」を用意
			AnswersDao answersDao = new AnswersDao();
						
			//#ArrayList(配列)としてAnswersBean型のansListを作り、そこにansDaoオブジェクト(AnswersDaoクラス)が持つfindメソッドを実行した戻り値を格納
			ArrayList<AnswersBean> answersList = (ArrayList<AnswersBean>) answersDao.find(questionsId);
									
			//取得できたレコードのanswerの件数をコンソールに出力して確認
			System.out.println(answersList.size());		
	
			//取得できたレコードの中身をコンソールに出力して確認
			for (int i = 0; i < answersList.size(); i++) {
								
				AnswersBean answersBean = answersList.get(i);
				
				System.out.println(answersBean.getAnswer());
				
			}
							
			//qbが取得した問題文questionをdelete_confirm.jspに渡す
			request.setAttribute("questionsBean",questionsBean);
							
			//ansListが取得した答えanswer(複数あるのでlistとして)をdelete_confirm.jspに渡す
			request.setAttribute("answersList", answersList);
	
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./delete_confirm.jsp");
			//フォワードの実行
			dispatcher.forward(request, response);
			
		} catch (Exception e11) {
					// TODO 自動生成された catch ブロック
					e11.printStackTrace();
			}
			
						
		}
					
	}
						

