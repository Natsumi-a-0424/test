package kadai;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//削除確認画面delete_confirm.jspから削除選択された問題idのパラメータを取得
		String id = null;
		id = request.getParameter("ID");
		
		//取得した問題idをコンソールに出力して確認
		System.out.println(id);
		
		
		
		//AnswersDao型のオブジェクト「ansDao」を用意
		AnswersDao ansDao = null;
		try {
			ansDao = new AnswersDao();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//int型のabを作り、そこにansDaoオブジェクト(AnswersDaoクラス)が持つdeleteメソッドを実行した戻り値を格納
		//deleteメソッドにint型のパラメータを渡すため、String→int型に変換
				int ab = 0;
					try {
						ab  =ansDao.delete(Integer.parseInt(id));
					} catch (DAOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
				
				//correct_answersテーブルの削除した行数をコンソールに出力して確認
				System.out.println(ab);		
				
		
				
				
		
		
		
		
		
		//#QuestionsDao型のオブジェクト、「queDao」を用意
		QuestionsDao queDao = null;
		try {
			queDao = new QuestionsDao();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//int型のqbを作り、そこにqueDaoオブジェクト(QuestionsDaoクラス)が持つdeleteメソッドを実行した戻り値を格納
		int qb = 0;
			try {
				qb  = queDao.delete(id);
			} catch (DAOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		
	
			//questionsテーブルの削除した行数をコンソールに出力して確認
			System.out.println(qb);
			
		

		
		
		
		
		
	
		
		
		
		
		
		
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./delete_result.jsp");
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
