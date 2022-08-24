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
		 * questionsテーブルからidとquestionをランダムで全件取得 
		 * 取得したものをsetAttributeしてtest.jspに遷移*/
		
		try {
			//QuestionsDaoオブジェクトをインスタンス化
			QuestionsDao questionsDao = new QuestionsDao();
				
			//実行結果を配列(ArrayList)として受け取る
			ArrayList<QuestionsBean> questionsList = (ArrayList<QuestionsBean>)questionsDao.random();
			
			//取得した配列の件数をコンソールに出力
			System.out.println(questionsList.size());
			
			//取得した配列をsetAttributeする
			request.setAttribute("questionsList", questionsList);
			
			//test.jspに遷移
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./test.jsp");//test.jspに遷移
	        //フォワードの実行
	        dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
