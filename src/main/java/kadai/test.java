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
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
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
		
		//test_result.jspで表示するユーザー名の受け取り
		String users_name = null;
		users_name = request.getParameter("users_name");
		
		System.out.println(users_name);
		
		request.setAttribute("users_name", users_name);
		
		/*流れ
		 * questionsテーブルからidとquestionをランダムで全件取得 
		 * 取得したものをsetAttributeしてtest.jspに遷移*/
		
		//QuestionsDaoオブジェクトをインスタンス化
		QuestionsDao queDao = null;
		try {
			queDao = new QuestionsDao();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//実行結果を配列(ArrayList)として受け取る
		ArrayList<QuestionsBean> queList = null;
		
		try {
			queList = (ArrayList<QuestionsBean>)queDao.random();
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//取得した配列の件数をコンソールに出力
		System.out.println(queList.size());
		
		//取得した配列をsetAttributeする
		request.setAttribute("queList", queList);
		
		//test.jspに遷移
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./test.jsp");//list.jspに遷移
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
