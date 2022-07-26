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
 * Servlet implementation class history
 */
@WebServlet("/history")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public history() {
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
		
		//top.jspからユーザー名の受け取り
		String users_name = null;
		users_name = request.getParameter("users_name");
		
		System.out.println(users_name);
		
		//HistoryDaoオブジェクトをインスタンス化
		HistoryDao hisDao = null;
		try {
			hisDao = new HistoryDao();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//ユーザー名をキーにして、HistoryDaoオブジェクトのもつfindメソッドで履歴を取得
		ArrayList<HistoryBean> hisList = null;
		try {
			hisList = (ArrayList<HistoryBean>) hisDao.find(users_name);
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//履歴の取得件数をコンソールに出力
		System.out.println(hisList.size());
		
		request.setAttribute("hisList",hisList);
		
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./history.jsp");
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
