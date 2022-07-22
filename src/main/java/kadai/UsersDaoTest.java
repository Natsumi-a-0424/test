package kadai;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
class UsersDaoTest {

	@Test
	public void findメソッドに存在するIDを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find(1).getName(),is("Yamada"));
		//UsersDaoのもつfindメソッドに存在するIDである1を渡して、ID1に該当するユーザー名のYamadaが返ってくるか検証(期待値がYamada)
				
	}
	
	@Test
	public void findメソッドに存在しないIDを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find(100).getName(),is(nullValue()));
		
				
	}
	
	@Test
	public void findメソッドにマイナス値を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find(-1).getName(),is(nullValue()));
	}	
	
	//find1メソッドはパラメーターに該当する行数を返す
	@Test
	public void find1メソッドに存在するIDを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("2"),is(1));
	}	
	
	@Test
	public void find1メソッドに存在しないIDを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("100"),is(0));
	}	
	
	@Test
	public void find1メソッドにマイナスの値を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("-1"),is(0));
	}
	
	@Test
	public void find1メソッドにnullを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1(null),is(0));
	}	
	
	@Test
	public void find1メソッドに機種依存文字を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("㍻"),is(0));
	}
	
	@Test
	public void find1メソッドに空文字を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1(""),is(0));
		
	}	
	
	@Test
	public void find1メソッドに記号を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("☆"),is(0));
	}
	
	@Test
	public void find1メソッドに全角を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("あ"),is(0));
		
	}	
	
	@Test
	public void find1メソッドに半角を渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("ｱ"),is(0));
	}	

}
