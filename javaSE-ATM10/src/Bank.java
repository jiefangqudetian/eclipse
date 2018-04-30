import java.util.HashMap;
import java.util.Map;

public class Bank {

	private Bank(){}
	
	private static Bank bank = new Bank();
	
	public static Bank getInstance() {
		return bank;
	}
	Map<String, Account> maps = new HashMap<>();


	
	
	
}
