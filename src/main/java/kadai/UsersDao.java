package kadai;


import java.sql.PreparedStatement;//JDBCのパッケージをインポート
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	//ConnectionDaoを継承したどのクラスからでもアクセス可能なUsersDaoクラスの定義
	public class UsersDao extends ConnectionDao {
		
		/**
		 * コンストラクタ
		 * @throws Exception 
		 */
		public UsersDao() throws Exception {
			setConnection();
		}

		/**
		 * users テーブルを全件取得
		 * @throws DAOException 
		 * @throws Exception 
		 */
		public List<UsersBean> findAll() throws DAOException  {//List<UsersBean>型findAllメソッドで例外が出た場合DAOExceptionに例外を投げる
			if (con == null) {//Connectionがnullのとき
				try {
					setConnection();//データベースと接続
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();//例外発生時までに実行したメソッドの時系列一覧表示
					
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためPreperedStatment型変数stを宣言
			ResultSet rs = null;//実行結果を格納するResultSetオブジェクトの設定
			try {
				String sql = "SELECT id, name, password FROM users WHERE delete_flag = 0";//Usersテーブルのdelete_flagが0のid,name,passwordを検索する
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文をデータベースに送る
				/** SQL 実行 **/
				rs = st.executeQuery();//実行結果をrsに格納
				/** select文の結果をArrayListに格納 **/
				List<UsersBean> list = new ArrayList<UsersBean>();//List(配列)としてUsersBean型のlistを作成
				while (rs.next()) {//繰返し
					int id = rs.getInt("id");//指定したカラムのidをint型で取得してint型変数idに格納
					String name = rs.getString("name");//指定したカラムのnameをString型で取得してString型変数nameに格納
					String pass = rs.getString("password");//指定したカラムのpasswordをString型で取得してString型変数passに格納
					UsersBean bean = new UsersBean(id, name, pass);//上記で取得したid,name,passを格納したUsersBeanオブジェクトbeanの作成
					list.add(bean);//beanのデータをlistに配列で格納
				}
				return list;//findAllメソッドで得たデータを配列で格納したlistを実行結果として返す
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			} finally {//例外が発生してもしなくても
				try {
					if (rs != null) {//rsがnullじゃなければ
							rs.close();//rs(sql文実行)を閉じる
					}
						
					if (st != null) {//stがnullじゃなければ
							st.close();//stを閉じる
							
					}
					close();//
				} catch (Exception e) {
					e.printStackTrace();//例外発生時までに実行したメソッドの時系列一覧
					throw new DAOException("リソースの開放に失敗しました");

				}
			}
		}

		
		/**
		 * 指定IDのレコードを取得する
		 * @throws Exception 
		 */
		public UsersBean find(int user_id) throws Exception {//int型変数user_idを使ってUsersBean型findメソッドを実行、例外はExceptionに投げる
			if (con == null) {//Connectionがnullのとき
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();//例外発生までに実行したメソッドの時系列一覧表示
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためPreperedStatment型変数stを宣言
			ResultSet rs = null;//データベースからの実行結果を格納するためのresultSet型変数rsの宣言
			try {
				/*usersテーブルのidが入力された値(user_id)に一致する行のid,name,passwordを検索*/
				String sql = "SELECT id, name, password FROM users WHERE id = ?";

			/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文をデータベースに送る
				st.setInt(1, user_id);//パラメータのインデックス1、パラメータがuser_id
				rs = st.executeQuery();//stが取得してきた実行結果を格納
				UsersBean bean = new UsersBean();//UsersBeanオブジェクトのbeanを作成
				while (rs.next()) {
					int id = rs.getInt("id");//指定したカラムから取得したidをint型変数idに格納
					String name = rs.getString("name");//指定したカラムから取得したnameをString型変数nameに格納
					String pass = rs.getString("password");//指定したカラムから取得したpasswordをString型変数passに格納
					bean.setId(id);//findメソッドで取得したidをbeanに格納
					bean.setName(name);//findメソッドで取得したnameをbeanに格納
					bean.setPassword(pass);//findでメソッドで取得したpassをbeanに格納
				}
				return bean;//結果をUsersBeanオブジェクトのbeanに返す
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			} finally {//例外が発生してもしなくても
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
		
		//int型変数input_idを使ってUsersBean型findメソッドを実行、例外はExceptionに投げる
		//int型の結果を返す
		public int find1(String input_id) throws Exception {
			if (con == null) {//Connectionがnullのとき
				try {
					setConnection();
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();//例外発生までに実行したメソッドの時系列一覧表示
				}
			}
			PreparedStatement st = null;//sql文をデータベースに送るためPreperedStatment型変数stを宣言
			ResultSet rs = null;//データベースからの実行結果を格納するためのresultSet型変数rsの宣言
			try {
				/*usersテーブルのidが入力された値(user_id)に一致する行を検索*/
				String sql = "SELECT id FROM users where id =  ?;";

			/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);//sql文をデータベースに送る
				st.setString(1, input_id);//パラメータのインデックス1、パラメータがinput_id
				rs = st.executeQuery();//stが取得してきた実行結果を格納
				int count = 0;
				while(rs.next()){
					int id = rs.getInt("id");
					count++;//一致する行数をカウント
					}
				
			return count;//一致する行数を返す
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました");
			} finally {//例外が発生してもしなくても
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
			
			public UsersBean findName(int login_id) throws Exception {
				if (con == null) {
					try {//Connectionがnullだったら
						setConnection();
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();//例外発生までに実行したメソッドの時系列一覧表示
					}
				}
				PreparedStatement st = null;//sql文をデータベースに送るためPreperedStatment型変数stを宣言
				ResultSet rs = null;//データベースからの実行結果を格納するためのresultSet型変数rsの宣言
				try {
					/*usersテーブルのidが入力された値(login_id)に一致する名前(name)を検索*/
					String sql = "SELECT name FROM users where id = ?;";

					/** PreparedStatement オブジェクトの取得**/
					st = con.prepareStatement(sql);//sql文をデータベースに送る
					st.setInt(1, login_id);//パラメータのインデックス1、パラメータがlogin_id
					rs = st.executeQuery();//stが取得してきた実行結果を格納
					UsersBean bean = new UsersBean();
					while(rs.next()){
						String name = rs.getString("name");
						bean.setName(name);
	
					}
					
					

				return bean;//
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました");
				} finally {//例外が発生してもしなくても
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
}
