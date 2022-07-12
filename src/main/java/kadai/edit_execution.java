package kadai;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class edit
 */
@WebServlet("/edit_execution")
public class edit_execution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit_execution() {
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
		
		
		/*流れ
		 * edit.jspで編集された問題文と答えをパラメータとして受け取る
		 * 受け取った問題文のパラメータをQuestionsDaoオブジェクトの持つupdateメソッドでデータ更新
		 * 同様に受け取った答えのパラメータをAnswersDaoオブジェクトのdeleteメソッドでデータ削除した後insertメソッドでデータ追加
		 * insert後は問題一覧画面list.jspに遷移*/
		
		//edit.jspで編集された問題文とそのidをパラメータとして受け取る
		String id = null;
		id = request.getParameter("id");
		
		String question = null;
		question = request.getParameter("question");
		
		/*edit.jspで編集された答えのquestions_idと入力された答え文をパラメータとして受け取る
		答え文は複数ある場合に合わせて配列での受け取り*/
		String questions_id = null;
		questions_id = request.getParameter("edit_questionsId");
		
		//答え文は複数ある場合に合わせて配列での受け取り
		String[] answer = null;
		answer = request.getParameterValues("editAnswer[]");
		
		//受け取ったパラメータをそれぞれコンソールに出力して確認
		System.out.println("更新する問題のidは" + id + "です");
		
		System.out.println("更新する問題文は" + question + "です");
		
		System.out.println("更新する答えのquestions_idは" + questions_id + "です");
		
		System.out.println("更新する答えは" + answer.length + "件です。");
		
		/*QuestionsDaoオブジェクトの持つupdateメソッドにてデータ内容更新
		QuestionsDaoオブジェクトのインスタンス化*/
		QuestionsDao queDao = null;
		try {
			queDao = new QuestionsDao();
			/*updateメソッドの戻り値をvoid型で設定しているので、戻り値格納用の変数はいらない
			updateメソッドにはidとquestionを渡す*/
			queDao.update(Integer.parseInt(id),question);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		/*答え文の更新(一度deleteしてinsert)
		 * 取得したquestions_idに対応する答え文を一度全削除*/
		
		//AnswersDaoオブジェクトの持つdeleteメソッドにquestions_idを渡す(削除行数を戻り値でもらう)
		AnswersDao ansDao = null;
		try {
			ansDao = new AnswersDao();
			//戻り値格納用の変数を用意
			int delete_row = 0;
			//deleteメソッドにint型のパラメータを渡すため、String→int型に変換
			delete_row = ansDao.delete(Integer.parseInt(questions_id));
			
			//削除した行数をコンソールに出力
			System.out.println(delete_row);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//AnswersDaoオブジェクトの持つinsertメソッドにquestions_idとanswerを渡す。戻り値なし。
		//questions_idはint型、answerは配列
		try {
			ansDao.insert(Integer.parseInt(questions_id),answer);
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		//insert後問題一覧画面list.jspに遷移
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./list");
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
