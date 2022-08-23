package kadai;


	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	//ConnectionDaoを継承したどこからでもアクセス可能のクラスQuestionsDaoの定義
	public class QuestionsDao extends ConnectionDao {
		
		/**
		 * コンストラクタ
		 * @throws Exception 
		 */
		public QuestionsDao() throws Exception {
			setConnection();
		}

		/**
		 * questionsテーブルを全件取得
		 * @throws DAOException 
		 * @throws Exception 
		 */
		//配列List<QuestionsBean>型findAllメソッドで例外発生時はDAOExceptionに投げる
		public List<QuestionsBean> findAll() throws DAOException  {
			if (con == null) {
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためPreperedStatment型変数stを宣言
			ResultSet rs = null;//実行結果を格納するためのResultSet型変数rsの宣言
			try {
				//questionsテーブルからidとquestionを検索するsql文
				String sql = "SELECT id, question FROM questions";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);
				/** SQL 実行 **/
				rs = st.executeQuery();//実行結果を格納
				/** select文の結果をArrayListに格納 **/
				List<QuestionsBean> list = new ArrayList<QuestionsBean>();//List(配列)としてQuestionsBean型のlistを作成
				while (rs.next()) {
					int id = rs.getInt("id");//rsが取得したidをint型で格納
					String que = rs.getString("question");//rsが取得したquestionをString型で格納
					QuestionsBean bean = new QuestionsBean(id, que);//QuestionsBean型変数beanに取得したidとquestionを格納
					list.add(bean);//idとquestionを格納しているbeanを配列としてlistに格納
				}
				return list;//findAllメソッド実行結果を格納したlistを結果として返す
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
		
		//String型変数qqを利用するQuestionsBean型を返すfindメソッド
		//String型変数qqは呼び出し元から渡された引数(delete_confirm.javaのid)
		public QuestionsBean find(String qq) throws DAOException {
		
			if (con == null) {
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためPrepareStatment型変数stを用意
			ResultSet rs = null;//sql文の実行結果を格納するResultSet型変数rsの用意
			try {
				//動的に条件を指定したい箇所を？にする
				//入力されたidと一致する行をquestionsテーブルから検索
				String sql = "SELECT * from questions where id=?;";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文の発行
				st.setString(1, qq);//パラメータのインデックスを1、パラメータがqq
				rs = st.executeQuery();//sql文の実行結果を格納
				QuestionsBean bean = new QuestionsBean();//QuestionsBeanオブジェクトbeanの作成
				//データの数だけ繰返し取り出す
				while (rs.next()) {
					int id1 = rs.getInt("id");//rsが取得したidをint型で格納
					String question1 = rs.getString("question");//rsが取得したquestionをString型で格納
					bean.setId(id1);//beanにid1を置換
					bean.setQuestion(question1);//beanにquestionを置換
				}
				return bean;//実行結果の格納されたbeanを返す
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
		//String型変数ddを利用してint型を返すdeleteメソッド、例外はDAOExceptionに投げる
		public int delete(String dd) throws DAOException{
			if (con == null) {
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためのPrepareStatment型変数stを宣言
			int rs = 0;//実行結果を格納するためのint型変数rsを宣言。削除行数の数字を返すためint型
			try {
				//動的に条件を指定したい箇所を？にする
				//questionsテーブルの中で、入力されたパラメータをidとして一致する行を削除するsql文の作成
				String sql = "DELETE from questions where id=?;";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文の発行
				st.setString(1, dd);//パラメータのインデックスを1、パラメータがdd
				rs = st.executeUpdate();//実行結果を格納

				return rs;//削除した行数を返す
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
		
				/**
				 * レコードの追加
				 * @throws Exception 
				 */
		//Srting型の変数rgstを使ってInt型を返すinsertメソッド
		//String型変数rgstはregister.javaから渡されたquestion
		public int insertAndGetId(String rgst) throws DAOException{
			if (con == null) {
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためのPrepareStatment型変数stの用意
			int rs = 0;//実行結果の行数を取得するためのint型変数rsの用意
			try {
				//questionsテーブルに追加する
				//questionsテーブルのquestionカラムにString型rgstのパラメータを追加(created_at,updata_atカラムの時刻はtimestampにて)
				String sql = "insert into questions (question, created_at,updated_at) values(?,now(),now());";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文をデータベースに向けて発行
				st.setString(1, rgst);//パラメータのインデックスを1、パラメータをrgstで設定
				rs = st.executeUpdate();//実行結果を格納
				
				 sql = "select max(id) as  LargestId from questions;";//questionsテーブルのidカラムの最大値をLargestIdとして取得
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文をデータベースに向けて発行
				ResultSet rs2 = null;//実行結果を格納するための変数rs2を用意
				rs2 = st.executeQuery();//実行結果を格納
				
				//データの数だけ取り出す
				int id1 = 0;//取得した最大値を格納するためのint型変数id1の用意
				while (rs2.next()) {//行数分繰り返す
					id1 = rs2.getInt("LargestId");//sql文の実行結果を格納したrs2からidの最大値を取得して格納
				}	
				

				
				return id1;//取得したidカラムの最大値を格納したid1を返す
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
		
		
		//問題文編集。指定したidのquestionを更新。
		public void update(int id, String question) throws DAOException{//Srting型の変数idとquestionを使うupdateメソッド(何も返さないのでvoid型)
			if (con == null) {//データベースへの接続がなかった場合
					try {
						setConnection();//データベースに接続
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();//例外発生時実行したメソッドまでの時系列一覧を表示
					}
			}
				
			
				PreparedStatement st = null;//sql文をデータベースに送るためのPreparedStatment型変数stの用意
				int rs = 0;//実行結果の行数を格納するint型変数rsの用意
				try {
					//questionsテーブルに追加する
					String sql = "update questions set question=?,  created_at=now(), updated_at=now() where id=?;";
					/** PreparedStatement オブジェクトの取得**/
					st = con.prepareStatement(sql);//データベースにsql分を発行
					st.setInt(2, id);//idパラメータのインデックスを1にセット
					st.setString(1,question);//questionパラメータのインデックスを2にセット
					rs = st.executeUpdate();//データ更新実行	
					
			
					
					return;
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

		public List<QuestionsBean> random() throws DAOException{
			if (con == null) {//データベースへの接続がなかった場合
				try {
					setConnection();//データベースに接続
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();//例外発生時実行したメソッドまでの時系列一覧を表示
				}
			}
			
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
		 
				String sql = "SELECT * FROM questions ORDER BY RAND();";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);
				/** SQL 実行 **/
				rs = st.executeQuery();//実行結果を格納
				/** select文の結果をArrayListに格納 **/
				List<QuestionsBean> list = new ArrayList<QuestionsBean>();//List(配列)としてQuestionsBean型のlistを作成
				while (rs.next()) {
					int id = rs.getInt("id");//rsが取得したidをint型で格納
					String que = rs.getString("question");//rsが取得したquestionをString型で格納
					QuestionsBean bean = new QuestionsBean(id, que);//QuestionsBean型変数beanに取得したidとquestionを格納
					list.add(bean);//idとquestionを格納しているbeanを配列としてlistに格納
				}
			
			return list;
			
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
		
	}
	
	
