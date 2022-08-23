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
 * Servlet implementation class delete_final_confirm
 */
@WebServlet("/delete_final_confirm")
public class delete_final_confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_final_confirm() {
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
		
			//削除確認画面delete_confirm.jspから削除選択された問題idのパラメータを取得
			String deleteQId = request.getParameter("deleteQId");
		
			//取得した問題idをコンソールに出力して確認
			System.out.println(deleteQId);

			AnswersDao answersDao = new AnswersDao();
	
			//int型のabを作り、そこにansDaoオブジェクト(AnswersDaoクラス)が持つdeleteメソッドを実行した戻り値を格納
			//deleteメソッドにint型のパラメータを渡すため、String→int型に変換
			int deleteRowA  =answersDao.delete(Integer.parseInt(deleteQId));
			
			//correct_answersテーブルの削除した行数をコンソールに出力して確認
			System.out.println(deleteRowA);		
			
			//#QuestionsDao型のオブジェクト、「queDao」を用意
			QuestionsDao queDao = new QuestionsDao();
		
			//int型のqbを作り、そこにqueDaoオブジェクト(QuestionsDaoクラス)が持つdeleteメソッドを実行した戻り値を格納
			int deleteRowQ  = queDao.delete(deleteQId);
		
			//questionsテーブルの削除した行数をコンソールに出力して確認
			System.out.println(deleteRowQ);
		
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./delete_result.jsp");
	        //フォワードの実行
			dispatcher.forward(request, response);
	
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}	
		
	}

}
