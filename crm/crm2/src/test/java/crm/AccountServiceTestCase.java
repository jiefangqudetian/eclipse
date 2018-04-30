package crm;

import java.util.List;

import org.junit.Test;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;

public class AccountServiceTestCase {

	AccountService service = new AccountService();
	
	@Test
	public void findAllAccountTest() {
		List<Account> accountList = service.findAllAccount();
		service.findAllAccount();
		System.out.println(accountList.size());
	}
}
