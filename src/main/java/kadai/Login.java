
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		
		
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		
		
		//#UsersDao型のオブジェクト、「dao」を用意
		//#ArrayList(配列)としてUsersBean型のlistを作り、そこにdaoオブジェクト(UsersDaoクラス)が持つfindAllメソッドを実行した戻り値を格納
		
			try {
				UsersDao dao = new UsersDao();//UsersDaoオブジェクトをインスタンス化	
				//daoオブジェクト(UsersDaoクラス)が持つfindAllメソッドを実行した戻り値を配列で格納
				ArrayList<UsersBean> list = (ArrayList<UsersBean>) dao.findAll();
			
			for (UsersBean data : list) {
		
			
			if(id.equals(String.valueOf(data.getId()))) {//login.jspから取得したパラメータidと、UsersDaoから取得した配列にあるidが一致
				
				
				//dataに格納してある配列から取得したpasswordを格納				
				if(pw.equals(data.getPassword())) {//login.jspから取得したpwと配列から取得したpasswordが一致
					
					/*test_result用に上記idとpwに一致するnameを取得してsetAttribute*/
					//request.setAttribute("users_name",data.getName());
					
					// セッションを取得
					HttpSession session = request.getSession();
					// ログイン判定処理
					// ログイン成功時 セッションにIDを入れる。
					session.setAttribute("loginId", id );
					
					//ログイン成功、TOPへ
					RequestDispatcher next =  request.getRequestDispatcher("./top.jsp");//top.jspに遷移
			        //フォワードの実行
			        try {
						next.forward(request, response);
					} catch (ServletException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}

					return;
					
				} else {
					
					
					//errorをパラメータにつける
					//loginへ	
					//リクエストスコープへの登録
					request.setAttribute("errorMessage","パスワードが違います" );
					//”パスワードが違います”を"errorMesage"に格納してsetAttribute
					break;//if文の処理をここで終了
					
				}
			} else {
				/*login.jspから取得したpwと配列から取得したpwが一致しなかった場合に
				表示する文言をerrorMessageに格納してsetAttribute*/
				request.setAttribute("errorMessage","IDが違います" );
			}	
		}

		
			
		
		RequestDispatcher dispatcher =  request.getRequestDispatcher("./login.jsp");
        //フォワードの実行
       
			dispatcher.forward(request, response);

			doGet(request, response);
		
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			// TODO 自動生成された catch ブロック
	}

}
