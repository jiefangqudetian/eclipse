import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Atm {

	Account account = new Account();
	List<Account> acclist = new ArrayList<>();
	Bank bank = Bank.getInstance();
	Scanner input = new Scanner(System.in);
	public void startup() {
		System.out.println("1.开户");
		System.out.println("2.插卡");
		System.out.println("3.系统管理");
		System.out.println("4.退出");
		System.out.println("请输入选项");
		switch (input.nextInt()) {
		case 1:
			
			System.out.println("请输入账户");
			String name = input.next();
			
			if(bank.findbyname(name)!=null) {
				System.out.println("账户已存在");
			} else {
				System.out.println("请输入密码");
				String password =input.next();
				Account acc = new Account();
				acc.setName(name);
				acc.setPassword(password);
				bank.acclist.add(acc);
				System.out.println("开户成功");
			}
			break;
		case 2:
			
			System.out.println("请输入账户");
			String name1 = input.next();
			System.out.println("请输入密码");
			String password1 = input.next();
			account = bank.nameMatchPassword(name1, password1);
			if(account==null){
				System.out.println("账户或密码错误");
			} else  {
				showAccountView();
			}
			break;
		case 3:
			
			System.out.println("请输入管理员密码");
			String password2 = input.next();
			if(password2.equals("123456")) {
				showMangageMenu();
			} else {
				System.out.println("密码输入错误");
			}
			break;
		case 4:
			System.exit(0);
			
			break;

		default:
			System.out.println("选项输入错误，请重新输入");
			break;
		}
		startup();
	}
		

	private void showMangageMenu() {
		// TODO Auto-generated method stub
		System.out.println("1.查看账户状态");
		System.out.println("2.冻结账户");
		System.out.println("3.解冻账户");
		System.out.println("4.退出");
		System.out.println("请输入选项");
		int num = input.nextInt();
		switch (num) {
		case 1:
			for(int i = 0; i < bank.acclist.size(); i++) {
				Account acc = new Account();
				System.out.println(acc.getName() + (acc.isState()?"正常" : "异常"));
			}
			break;
		case 2:
			System.out.println("请输入要冻结账户");
			String name = input.next();
			Account acc2 = bank.findbyname(name);
			if(acc2==null) {
				System.out.println("账户不存在");
			} else{
				acc2.setState(false);
			}
			break;
		case 3:
			System.out.println("请输入要解冻账户");
			String name1 = input.next();
			Account acc3 = bank.findbyname(name1);
			if(acc3==null) {
				System.out.println("账户不存在");
			} else{
				acc3.setState(true);
			}
			break;
			
		case 4:
			startup();
			break;

		default:
			break;
		}
	}
	
	private void showAccountView() {
		// TODO Auto-generated method stub
		System.out.println("1.查询余额");
		System.out.println("2.存款");
		System.out.println("3.取款");
		System.out.println("4.转账");
		System.out.println("5.修改密码");
		System.out.println("6.退出");
		System.out.println("请输入选项");
		switch (input.nextInt()) {
		case 1:
			System.out.println(account.getMoney());
			break;
		case 2:
			System.out.println("请输入存款额");
			int inmoney = input.nextInt();
			if(inmoney<0){
				System.out.println("金额输入错误");
			} else {
				account.setMoney(inmoney+account.getMoney());
				System.out.println("存款成功");
			}
			break;
		case 3:
			System.out.println("请输入取款额");
			int outmoney = input.nextInt();
			if(outmoney<0 || outmoney>account.getMoney()) {
				System.out.println("金额输入错误");
			} else {
				account.setMoney(account.getMoney()-outmoney);
				System.out.println("取款成功");
			}
			break;
		case 4:
			System.out.println("请输入转账账户");
			String name = input.next();
			Account acc = bank.findbyname(name);
			if(acc==null) {
				System.out.println("账户输入错误");
			} else {
				System.out.println("请输入转账额");
				int tranmoney = input.nextInt();
				if(tranmoney<0 || tranmoney>account.getMoney()) {
					System.out.println("金额输入错误");
				} else {
					account.setMoney(account.getMoney()-tranmoney);
					acc.setMoney(acc.getMoney()+tranmoney);
					System.out.println("转账成功");
				}
				
			}
			
			break;
		case 5:
			System.out.println("请输入原始密码");
			String oldpassword = input.next();
			if(account.getPassword().equals(oldpassword)) {
				System.out.println("请输入新密码");
				String newpassword = input.next();
				account.setPassword(newpassword);
			} else {
				System.out.println("密码输入错误");
			}
			break;
		case 6:
			startup();
			break;

		default:
			break;
		}
		
		
		showAccountView();
	}
}
