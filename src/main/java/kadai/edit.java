package kadai;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		/*流れ
		 * list.jspから選択された問題のidをパラメータとして取得
		 * 取得した問題のidをキーにして問題文を取得
		 * 同様に問題のidをキーにして対応する答えを取得する
		 * 取得した問題文と答えをedit_confirm.jspに渡す*/
		
		
		
		//list.jspから編集する問題のidを取得する
		//取得するパラメータ(問題のid)を格納する変数の宣言(getParameterの戻り値はString型)
		String id = null;
		//パラメータを取得
		id = request.getParameter("ID");
		
		//取得したパラメータをコンソールに出力して確認
		System.out.println("編集する問題のidは" + id + "です");
		
		//取得した問題のidをキーにして、付随するレコード(question)のを取得
		//QuestionsDaoオブジェクトのfindメソッドを利用
		//QuestionsDaoオブジェクトのインスタンス化
		QuestionsDao queDao = null;
		try {
			queDao = new QuestionsDao();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//queDaoオブジェクト(QuestionsDaoクラス)がもつfindメソッドを呼び出し、questionを取得
		//findメソッドの戻り値を格納するQuestionsBean型の変数を用意
		QuestionsBean qb = null;
		
		//queDaoオブジェクト(QuestionsDaoクラス)がもつfindメソッドを呼び出し、QeustionsBean型で戻り値を格納
		try {
			qb = (QuestionsBean)queDao.find(id);//idをキーにして付随するquestionをfindして格納
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//qbが取得できたquestionをコンソールに出力して確認
		System.out.println("編集する問題は" + qb.getQuestion() + "です");
		
		
		//問題のidをキーにしてquestions_idが一致する答えを取得する(答えが複数の場合もあるので配列を使用)
		//AnswersDaoオブジェクトのインスタンス化
		AnswersDao ansDao = null;
		try {
			ansDao = new AnswersDao();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//配列ArrayList<AnswersBean>型の変数を用意し、ansDaoオブジェクト(AnswersDaoクラス)のもつfindメソッドの戻り値をArrayList<AnswersBean>型で格納
		ArrayList<AnswersBean> ansList = null;
		
		try {
			ansList = (ArrayList<AnswersBean>)ansDao.find(id);//findメソッドにgetParameterしたidを持たせてる
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//取得したanswerの件数をコンソールに出力して確認
		System.out.println("編集する答えの件数は" + ansList.size() + "件です");
		
		//取得した問題文questionと答えanswerをsetAttributeしてedit_confirm.jspに渡す
		
		request.setAttribute("editQuestion",qb);//qbの中身はidとquestion
		
		request.setAttribute("editAnswer",ansList);//ansListの中身はid,questions_id,answer
		
		//edit.jspに遷移
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./edit.jsp");
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
