
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
		
		//getParameterメソッド使用してデータ取得、取得した値をString型の変数に代入
		String id = null;//String型変数idの宣言
		id = request.getParameter("ID");//login.jspのテキストボックスIDに入力された値を取得
		String pw = null;//String型変数psの宣言
		pw = request.getParameter("PW");//login.jspのテキストボックスPWに入力された値を取得
		
		
		//#UsersDao型のオブジェクト、「dao」を用意
		UsersDao dao = null;//UsersDao型変数daoの宣言
		try {
			dao = new UsersDao();//UsersDaoオブジェクトをインスタンス化	
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();//例外発生時までに実行したメソッドの時系列一覧を表示
		}

		//#ArrayList(配列)としてUsersBean型のlistを作り、そこにdaoオブジェクト(UsersDaoクラス)が持つfindAllメソッドを実行した戻り値を格納
		ArrayList<UsersBean> list = null;//#ArrayList(配列)としてUsersBean型のlistを宣言
			try {
				//daoオブジェクト(UsersDaoクラス)が持つfindAllメソッドを実行した戻り値を配列で格納
				list = (ArrayList<UsersBean>) dao.findAll();
			} catch (DAOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			// TODO 自動生成された catch ブロック

		
		
		UsersBean data = null;//listに格納されている配列から取り出した要素を格納するためのUsersBean型変数dataの宣言
		
		/*変数dataの宣言と初期化、、条件式評価trueで繰返し実行で変数iの値出力、繰返し一回目終了
		 * 変化式で変数iが1増加（i=1）、繰返し実行2回目終了、変化式で変数iが1増加（i=2）、
		 * 変数iがリストの要素より小さいうちは繰返し実行*/
		for(int i = 0;  i <list.size() ; i++) {//findAllメソッドで取得した配列分繰返し
			
			data = list.get(i);//findAllメソッドで取得した配列のi番目の値を格納
		
			//繰り返しの中で実行される内容
			//login.jspから取得したパラメータidとlist内のi番目のidと等しければ、次にPWがlist内のi番目と等しいか
			//String型変数strに、dataに格納されている配列から取得したidをint型からString型に変換して格納
			String str = String.valueOf(data.getId());
			if(id.equals(str)) {//login.jspから取得したパラメータidと、UsersDaoから取得した配列にあるidが一致
				
				
				str = data.getPassword();//dataに格納してある配列から取得したpasswordを格納				
				if(pw.equals(str)) {//login.jspから取得したpwと配列から取得したpasswordが一致
					
					/*test_result用に上記idとpwに一致するnameを取得してsetAttribute*/
					str = data.getName();
					System.out.println(str);
					request.setAttribute("users_name",str);
					
					//TOPへ

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
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		
	
		
		
		try {
			doGet(request, response);
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
