package kadai;

public class HistoryBean{
	private String users_name;
	private int score;
	private String scoring_time;
	
	/**
	 * コンストラクタ
	 */
		public HistoryBean(String users_name, int score, String scoring_time) {
			this.users_name = users_name;
			this.score = score;
			this.scoring_time = scoring_time;
		}
		
		/** 引数無しのコンストラクタ **/
		public HistoryBean() {

		}
	
	public String getUsers_name() {
		return users_name;
	}
	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getScoring_time() {
		return scoring_time;
	}
	public void setScoring_time(String scoring_time) {
		this.scoring_time = scoring_time;
	}
}
