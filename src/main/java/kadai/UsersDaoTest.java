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
	
	
	@Test
	public void find1メソッドに存在するIDを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1("2").getId(),is("2"));
	}	
	
	@Test
	public void find1メソッドに存在しないIDを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1().);
	}	
	
	@Test
	public void find1メソッドにを渡す() throws Exception{
		UsersDao usersDao = new UsersDao();
		assertThat(usersDao.find1().);
	}	

}
