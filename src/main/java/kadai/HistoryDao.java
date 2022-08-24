package kadai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao extends ConnectionDao {
	
	public HistoryDao() throws Exception {
		setConnection();
	}
	
	
	/**
	 * レコードの追加
	 * @throws Exception 
	 */
	//Srting型の変数rgstを使ってInt型を返すinsertメソッド
	//String型変数rgstはregister.javaから渡されたquestion
	public int insert(int login_id, double point, String created_at) throws DAOException{
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
			String sql = "insert into histories (user_id, point, created_at) values(?,?,?);";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);//sql文をデータベースに向けて発行
			st.setInt(1, login_id);//インデックス1にパラメータをuser_idを指定
			st.setDouble(2, point);//インデックス2にパラメータをpointを指定
			st.setString(3,created_at);//インデックス3にパラメータをcreated_atを指定
			rs = st.executeUpdate();//実行結果を格納		
		
			
			return rs;//取得したidカラムの最大値を格納したid1を返す
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
	 * 指定users_idのレコードを取得する
	 * @throws Exception 
	 */
	//String型変数idを利用するList<AnswersBean>型で返すfindメソッドを定義
	public List<HistoryBean> find(int loginId) throws DAOException {
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
			String sql = "SELECT * from histories where user_id=? order by created_at;";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);//データベースに向けてsql文の発行
			st.setInt(1, loginId);//パラメータのインデックスを1
			rs = st.executeQuery();
			
			List<HistoryBean> list = new ArrayList<HistoryBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int point = rs.getInt("point");
				String created_at = rs.getString("created_at");
				HistoryBean bean = new HistoryBean(id, user_id, point, created_at);
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
	
}