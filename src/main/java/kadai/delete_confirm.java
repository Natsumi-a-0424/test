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
 * Servlet implementation class delete_confirm
 */
@WebServlet("/delete_confirm")
public class delete_confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_confirm() {
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
		
		
		
		//getParameterメソッド使用してlist.jspから削除する問題番号(id)を取得、取得した値をString型の変数idに代入
				String id = null;
				id = request.getParameter("ID");
				
				//list.jspから受け取った問題のidをコンソールに表示
				System.out.println(id);
				
                 request.setAttribute("ID",id);	
				
				//#QuestionsDao型のオブジェクト、「queDao」を用意
				QuestionsDao queDao = null;
				try {
					queDao = new QuestionsDao();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
				//QuestionsBean型のqbを作り、そこにqueDaoオブジェクト(QuestionsDaoクラス)が持つfindメソッドを実行した戻り値を格納
				QuestionsBean qb = null;
					try {
						//パラメータを格納したidを引数としてqueDaoオブジェクトがもつfindメソッドに渡して、その実行結果を格納
						qb  = (QuestionsBean)queDao.find(id);
					} catch (DAOException e1) {
						// TODO 自動生成された catch ブロック
						e1.printStackTrace();
					}
					
				
				
				//# 取得できたレコードのquestionをコンソールに出力して確認
				System.out.println(qb.getQuestion());
				
				
				//AnswersDao型のオブジェクト「ansDao」を用意
				AnswersDao ansDao = null;
				try {
					ansDao = new AnswersDao();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				
				//#ArrayList(配列)としてAnswersBean型のansListを作り、そこにansDaoオブジェクト(AnswersDaoクラス)が持つfindメソッドを実行した戻り値を格納
					ArrayList<AnswersBean> ansList = null;
						try {
							ansList = (ArrayList<AnswersBean>) ansDao.find(id);
						} catch (DAOException e11) {
							// TODO 自動生成された catch ブロック
							e11.printStackTrace();
						}
						
						//取得できたレコードのanswerの件数をコンソールに出力して確認
						System.out.println(ansList.size());		
						
				
						
						//取得できたレコードの中身をコンソールに出力して確認
						for (int i = 0; i < ansList.size(); i++) {
							
							AnswersBean aa = ansList.get(i);
			
						    System.out.println(aa.getAnswer());
						    }
						
						//qbが取得した問題文questionをdelete_confirm.jspに渡す
						request.setAttribute("qb",qb);
						
						//ansListが取得した答えanswer(複数あるのでlistとして)をdelete_confirm.jspに渡す
						request.setAttribute("ansList", ansList);
						
						
						
						
						
					
						
						
						RequestDispatcher dispatcher =  request.getRequestDispatcher("./delete_confirm.jsp");
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
						
						
						
			
							
						
						
						
						
							
				
		
		
	
	
	
	
	



