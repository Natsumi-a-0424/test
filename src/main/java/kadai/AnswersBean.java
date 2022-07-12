package kadai;


	import java.sql.Timestamp;
	public class AnswersBean {
		private int id;
		private int questions_id;
		private String answer;
		private Timestamp created_at;
		private Timestamp updated_at;

	/**
	 * コンストラクタ
	 */
		public AnswersBean(int id, int questions_id, String answer) {
			this.id = id;
			this.questions_id = questions_id;
			this.answer = answer;
			
		}
		
		/** 引数無しのコンストラクタ **/
		public AnswersBean() {

		}

		public int getId() {
			return this.id;
		}
		public void setId(int id) {
			this.id = id;
		} 
		public int getQuestions_id() {
			return this.questions_id;
		}	
		public void setQuestions_id(int questions_id) {
			this.questions_id =questions_id ;
		}
		public String getAnswer() {
			return this.answer;
		}	
		public void setAnswer(String answer) {
			this.answer = answer ;
		}
		public Timestamp getCreatedAt() {
			return this.created_at;
		}
		public void setCreatedAt(Timestamp created_at) {
			this.created_at = created_at;
		}
		public Timestamp getUpdatedAt() {
			return this.updated_at;
		}
		public void setUpdatedAt(Timestamp updated_at) {
			this.updated_at = updated_at;
		}
	}


