package kadai;


	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	//どのクラスからでもアクセスでき、ConnectionDaoを継承しているAnswersDaoクラスの定義
	public class AnswersDao extends ConnectionDao {
		
		/**
		 * コンストラクタ
		 * @throws Exception 
		 */
		public AnswersDao() throws Exception {
			setConnection();
		}

		/**
		 * correct_answersテーブルを全件取得
		 * @throws DAOException 
		 * @throws Exception 
		 */
		//実行結果を配列ListのAnswersDao型で返すfindAllメソッドの定義
		public List<AnswersBean> findAll() throws DAOException  {
			if (con == null) {
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためPreperedStatment型変数stを宣言
			ResultSet rs = null;
			try {
				//correct_answersテーブルのid,questions_id,answerカラムのデータを取り出すsql文
				String sql = "SELECT id, questions_id, answer FROM correct_answers";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//データベースに向けてsql文の発行
				/** SQL 実行 **/
				rs = st.executeQuery();//実行結果を格納
				/** select文の結果をArrayListに格納 **/
				List<AnswersBean> list = new ArrayList<AnswersBean>();//実行結果を配列として格納するため、List<AnsersBean>型listの作成
				while (rs.next()) {//行数分繰返し実行
					int id = rs.getInt("id");//rsが取得した実行結果からidを取得して格納
					int questions_id = rs.getInt("questions_id");//rsが取得した実行結果からquestions_isを取得して格納
					String answer = rs.getString("answer");//rsが取得した実行結果からanswerを取得して格納
					//AnswersBeanオブジェクトbeanを作成して引数をid,questions_id,answerに設定
					AnswersBean bean = new AnswersBean(id, questions_id, answer);
					list.add(bean);//beanをlistに追加
				}
				return list;//配列listを返す
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			} finally {
				try {
					if (rs != null) {
							rs.close();
					}
						
					if (st != null) {
							st.close();
					}
					close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("リソースの開放に失敗しました");

				}
			}
		}

		/**
		 * 指定IDのレコードを取得する
		 * @throws Exception 
		 */
		//String型変数idを利用するList<AnswersBean>型で返すfindメソッドを定義
		public List<AnswersBean> find(String id) throws DAOException {
			if (con == null) {//conがnullのとき
				try {
					setConnection();//データベースに接続
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();//例外発生時までに実行したメソッドの時系列一覧を表示
				}
			}
			PreparedStatement st = null;//sql文をデータベースに渡すためのsql文をデータベースに送るためPreperedStatment型変数stを宣言
			ResultSet rs = null;//実行結果を格納するためのResultSet型変数rsを宣言
			try {
				//correct_answersテーブルの中で、渡されたパラメータをquestions_idとして一致する行を取得するsql文を作成
				String sql = "SELECT * from correct_answers where questions_id=?;";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//データベースに向けてsql文の発行
				st.setString(1, id);//パラメータのインデックスを1
				rs = st.executeQuery();
				
				List<AnswersBean> list = new ArrayList<AnswersBean>();
				while (rs.next()) {
					int id1 = rs.getInt("id");
					int questions_id = rs.getInt("questions_id");
					String answer = rs.getString("answer");
					AnswersBean bean = new AnswersBean(id1, questions_id, answer);
					list.add(bean);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			} finally {
				try {
					if (rs != null){
							rs.close();
					}				
					if (st != null) {
							st.close();
					}
					close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("リソースの開放に失敗しました");
				}
		}
	}
		
	
	
		
	
		
		/**
		 * 指定IDのレコードを削除する
		 * @throws Exception 
		 */
		
		//int型変数ddを利用して、戻り値をint型で返すdeleteメソッド
		public int delete(int dd) throws DAOException {
			if (con == null) {
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			PreparedStatement st = null;
			int rs = 0;
			try {
				//correct_answersテーブルの中で取得したパラメータに一致する行を削除
				String sql = "DELETE from correct_answers where questions_id=?;";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);
				st.setInt(1, dd);
				rs = st.executeUpdate();
				
					
				return rs;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			} finally {
				try {
								
					if (st != null) {
							st.close();
					}
					close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("リソースの開放に失敗しました");
				
		
				}
				
			}
				
				
		}
			
			public void insert(int questions_id,String[] rgst) throws DAOException{//int型変数questions_idと、配列のSrting型の変数rgstを使うinsertメソッド(何も返さないのでvoid型)
				if (con == null) {
					try {
						setConnection();
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
				PreparedStatement st = null;
				int rs = 0;
				try {
					//correct_answersテーブルに追加する
					String sql = "insert into correct_answers (questions_id, answer,created_at,updated_at) values(?,?,now(),now());";
					/** PreparedStatement オブジェクトの取得**/
					for(int i =0; i<rgst.length; i++) {
					
						st = con.prepareStatement(sql);
						st.setInt(1, questions_id);
						st.setString(2,rgst[i]);
						rs = st.executeUpdate();	
					}
					
					

					
					return;//void型なので何も返さない
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました");
				} finally {
					try {
									
						if (st != null) {
								st.close();
						}
						close();
					} catch (Exception e) {
						e.printStackTrace();
						throw new DAOException("リソースの開放に失敗しました");
					}
				
				
								
			
				}
			}
}
