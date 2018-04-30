import java.util.ArrayList;
import java.util.List;

public class Bank {

	private Bank(){}
	
	private static Bank bank = new Bank();
	
	public static Bank getInstance() {
		return bank;
	}
	List<Account> acclist = new ArrayList<>();
	
	public Account findbyname(String name) {
		for(int i = 0; i < acclist.size(); i++) {
			if(acclist.get(i).getName().equals(name)) {
				return acclist.get(i);
			}
		}
		return null;
	}
		
	public boolean savename(String name,String password) {
		Account account = findbyname(name);
		if(account==null) {
			return false;
		} else {
			account.setName(name);
			account.setPassword(password);
			return true;
		}
		
	}
	
	public Account nameMatchPassword(String name,String password) {
		for(int i = 0; i < acclist.size(); i++) {
			if(acclist.get(i).getName().equals(name)) {
				if(acclist.get(i).getPassword().equals(password)) {
					return acclist.get(i);
				}
			}
		}
		return null;
	}
}
