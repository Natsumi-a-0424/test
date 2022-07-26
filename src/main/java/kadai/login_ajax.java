package kadai;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login_ajax
 */
@WebServlet("/login_ajax")
public class login_ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login_ajax() {
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
		
		String id = null;
		id = request.getParameter("ID");//login.jspでパラメータをjsファイルを通して取得
		
		System.out.println(id);//取得したパラメータをコンソールに出力して確認
		
		UsersDao dao = null;
		try {
			dao = new UsersDao();//UsersDaoオブジェクトをインスタンス化
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		int num = 0;
		try {
			//find1にString型のidを渡す
			//
			num = Integer.valueOf(dao.find1(id));
			
			System.out.println(num);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		PrintWriter pw = response.getWriter();
		pw.print(num);
	}

}
