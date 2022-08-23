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

			//loginIdをString型からint型へ変換
			String loginId = (String)session.getAttribute("loginId");
			int intLoginId = Integer.parseInt(loginId); 
			
			//history.jspで表示するユーザー名の取得
			UsersDao dao = new UsersDao();
			UsersBean userName = (UsersBean)dao.findName(intLoginId);
			
			System.out.println(userName.getName());
			
			request.setAttribute("userName", userName.getName());
			
			//HistoryDaoオブジェクトをインスタンス化
			HistoryDao historyDao = new HistoryDao();
			
			//intloginIdをキーにして、HistoryDaoオブジェクトのもつfindメソッドで履歴を取得
			ArrayList<HistoryBean> historyList = (ArrayList<HistoryBean>) historyDao.find(intLoginId);
		
		
			//履歴の取得件数をコンソールに出力
			System.out.println(historyList.size());
		
			request.setAttribute("historyList",historyList);
		
			RequestDispatcher dispatcher =  request.getRequestDispatcher("./history.jsp");
			//フォワードの実行
			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}

}
