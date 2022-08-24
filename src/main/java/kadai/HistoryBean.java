package kadai;

public class HistoryBean{
	private int id;
	private int user_id;
	private int point;
	private String created_at;
	
	/**
	 * コンストラクタ
	 */
		public HistoryBean(int id,int user_id, int point, String created_at) {
			this.id = id;
			this.user_id = user_id;
			this.point = point;
			this.created_at = created_at;
		}
		
		/** 引数無しのコンストラクタ **/
		public HistoryBean() {

		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
