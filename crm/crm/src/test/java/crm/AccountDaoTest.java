package crm;


import java.sql.Timestamp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.entity.Account;

public class AccountDaoTest {
	private AccountDao accountDao = null;
	@Before
	public void before() {
		accountDao = new AccountDao();
		System.out.println("before");
	}
	@Test
	public void saveTest() {
		Account account = new Account();
		account.setUsername("www");
		account.setPassword("000000");
		account.setMobile("798");
		account.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		accountDao.save(account);
	}
	@Test
	public void findByIdTest() {
		int accountId = 7;
		Account account = accountDao.findById(accountId);
		Assert.assertNotNull(account);
	}
	@After
	public void after() {
		System.out.println("单元测试已通过，请查看结果");
	}
}
