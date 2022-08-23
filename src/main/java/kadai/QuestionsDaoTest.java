package kadai;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;

class QuestionsDaoTest {

	//findメソッドはidに一致する問題文が返される
	@Test
	public void findメソッドに存在する問題のidを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("4").getQuestion(),is("sampleQ4"));
	}	
	
	@Test
	public void findメソッドに存在しない問題のidを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("1").getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドにマイナスの値を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("-4").getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドにnullを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find(null).getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドに空文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("").getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドに記号を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("☆").getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドに半角を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("ｱ").getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドに全角を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("あ").getQuestion(),is(nullValue()));
	}
	
	@Test
	public void findメソッドに小数を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("0.03").getQuestion(),is(nullValue()));
	}	
	
	@Test
	public void findメソッドに機種依存文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.find("㍻").getQuestion(),is(nullValue()));
	}
	
	//deleteメソッドは削除行数が返される
	
	//存在する問題のidを渡すと実際に削除されるのでテスト実行前にidの再入力が必要
	@Test
	public void deleteメソッドにDBに存在する問題のidを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("15"),is(1));
	}	
	
	@Test
	public void deleteメソッドにDBに存在しない問題のidを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("5000"),is(0));
	}	
	
	@Test
	public void deleteメソッドにマイナスの値を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("-6"),is(0));
	}	
	
	@Test
	public void deleteメソッドにnullを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete(null),is(0));
	}	
	
	@Test
	public void deleteメソッドに空文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete(""),is(0));
	}	
	
	/*エラー
	@Test
	public void deleteメソッドに記号を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("☆"),is(0));
	}
	
	@Test
	public void deleteメソッドに半角文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("ｱ"),is(0));
	}	
	
	@Test
	public void deleteメソッドに全角文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("あ"),is(0));
	}
	*/	

	@Test
	public void deleteメソッドに小数を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("0.07"),is(0));
	}	

	@Test
	public void deleteメソッドに機種依存文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.delete("㍻"),is(0));
	}	

	//insertメソッドは問題文のidの最大値を返す
	
	//テスト実行時に毎回最大値を取得する
	/*
	@Test
	public void insertメソッドに全角文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert("あ"),is(17));
	}
	
	@Test
	public void insertメソッドに半角文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert("ｱ"),is(18));
	}
	
	@Test
	public void insertメソッドにマイナスの値を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert("-45"),is(19));
	}
	
	@Test
	public void insertメソッドに記号を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert("☆"),is(20));
	}
	
	@Test
	public void insertメソッドにnullを渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert(null),is(nullValue()));
	}
	
	@Test
	public void insertメソッドに機種依存文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert("㍻"),is(21));
	}
	
	@Test
	public void insertメソッドに空文字を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert(""),is(22));
	}
	
	@Test
	public void insertメソッドに小数を渡す() throws Exception{
		QuestionsDao questionsDao = new QuestionsDao();
		assertThat(questionsDao.insert("0.01"),is(23));
	}
	*/

}
