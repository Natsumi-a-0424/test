
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
		
		//getParameterメソッド使用してデータ取得、取得した値をString型の変数に代入
				String id = null;
				id = request.getParameter("ID");
				String que = null;
				que = request.getParameter("QUESTION");
				String ansId = null;
				ansId = request.getParameter("ANSID");
				String queId = null;
				queId = request.getParameter("QUEID");
				String ans = null;
				ans = request.getParameter("ANSWER");

				
				
			//#QuestionsDao型のオブジェクト、「queDao」を用意
			QuestionsDao queDao = null;//変数宣言
			try {
				queDao = new QuestionsDao();//オブジェクト作成
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();//例外発生時までに実行したメソッドの時系列一覧表示
			}
			
			//#ArrayList(配列)としてQuestionsBean型のqueListを作り、そこにqueDaoオブジェクト(QuestionsDaoクラス)が持つfindAllメソッドを実行した戻り値を格納
			ArrayList<QuestionsBean> queList = null;//findAllメソッド戻り値を配列として格納する変数queListの宣言
				try {
					queList = (ArrayList<QuestionsBean>) queDao.findAll();//queDaoオブジェクトがもつfindAllメソッドの戻り値を配列として格納
				} catch (DAOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				// TODO 自動生成された catch ブロック
			
			//# 取得できたレコードのquestionsの件数をコンソールに出力して確認
			System.out.println("取得したquestionsの件数は"+ queList.size() + "件です");
			
			//AnswersDao型のオブジェクト「ansDao」を用意
			AnswersDao ansDao = null;//変数宣言
			try {
				ansDao = new AnswersDao();//オブジェクトの作成
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
			//#ArrayList(配列)としてAnswersBean型のansListを作り、そこにansDaoオブジェクト(AnswersDaoクラス)が持つfindAllメソッドを実行した戻り値を格納
				ArrayList<AnswersBean> ansList = null;//findAllメソッドの戻り値を配列として格納する変数ansListの宣言
					try {
						ansList = (ArrayList<AnswersBean>) ansDao.findAll();//ansDaoオブジェクトがもつfindAllメソッドの戻り値を配列として格納
					} catch (DAOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
					// TODO 自動生成された catch ブロック

				//# 取得できたレコードのcorrect_answersの件数をコンソールに出力して確認
				System.out.println("取得したanswerの件数は" + ansList.size() + "件です");
					
				
				
				request.setAttribute("queList",queList );//queDaoオブジェクトfindAllメソッドの戻り値の配列をsetAttribute
				
				request.setAttribute("ansList",ansList );//ansDaoオブジェクトfindAllメソッドの戻り値の配列をsetAttribute
				
				
			
				

				RequestDispatcher dispatcher =  request.getRequestDispatcher("./list.jsp");//list.jspに遷移
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


