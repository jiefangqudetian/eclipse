package atm;

import java.util.List;
import java.util.Scanner;

import dao.AccountDao;
import entity.Account;

public class Atm {

	//调用Dao中的方法有 findbyname findall save update 
	AccountDao Dao = new AccountDao();
	Account account = new Account();  //整个系统需要一个当前账户 ，一个Dao
	Scanner input = new Scanner(System.in);
	public void startup() {
		System.out.println("1.开户\n2.插卡\n3.系统管理\n4.退出\n请输入选项");
		String option = input.next();
		if (option.equals("1")) {
			createAccount();
		} else if (option.equals("2")) {
			insertAccount();
		} else if (option.equals("3")) {
			manageSystem();
		} else if (option.equals("4")) {
			System.exit(0);
		} else {
			System.out.println("选项输入错误，请重新输入");
		}
		startup();
	}

	/**
	 * 3.系统管理
	 */
	private void manageSystem() {
		// TODO Auto-generated method stub
		System.out.println("请输入管理员密码");
		String password = input.next();
		if (password.equals("123456")) {
			showManageMenu();
		} else {
			System.out.println("密码输入错误");
		}
	}

	/**
	 * 2.插卡
	 */
	private void insertAccount() {
		// TODO Auto-generated method stub
		System.out.println("请输入账户");
		String name = input.next();
		System.out.println("请输入密码");
		String password = input.next();
		account = Dao.findByName(name); //查找账户，然后赋值给acctemp，再判断acctemp的密码和输入密码
		if (account != null && account.getPassword().equals(password)) {
			if (account.getState().equals("正常")) {
				System.out.println("登录成功");
				showAccountView();
			} else {
				System.out.println("账户被冻结，请联系管理员");
			}
		} else {
			System.out.println("账户或密码输入错误");
		}
	}

	/**
	 * 1.开户
	 */
	private void createAccount() {
		// TODO Auto-generated method stub
		System.out.println("请输入账户");
		String name = input.next();

		if (Dao.findByName(name) != null) {
			System.out.println("账户已存在");
		} else {
			System.out.println("请输入密码");
			String password = input.next();
			Account acctemp = new Account();
			acctemp.setName(name);
			acctemp.setPassword(password);
			Dao.save(acctemp);
			System.out.println("开户成功");
		}
	}

	/**
	 * 管理员二级菜单
	 */
	private void showManageMenu() {
		System.out.println("1.查看账户状态\n2.冻结账户\n3.解冻账户\n4.退出\n请输入选项");
		String option = input.next();
		if (option.equals("1")) {
			List<Account> list = Dao.findAll();
			System.out.println("账户名：\t\t状态：" ); //制表位补全作用
			for (Account account : list) {
				System.out.println(account.getName()+"\t\t"+account.getState());
			}
		} else if (option.equals("2")) {
			System.out.println("请输入要冻结账户");
			String name = input.next();
			Account acctemp = Dao.findByName(name) ;
			if (acctemp!=null) {
				acctemp.setState("异常");
				Dao.update(acctemp);
				System.out.println("冻结成功");
			} else {
				System.out.println("账户不存在");
			}
		} else if (option.equals("3")) {
			System.out.println("请输入要解冻账户");
			String name = input.next();
			Account acctemp = Dao.findByName(name) ;
			if (acctemp!=null) {
				acctemp.setState("正常");
				Dao.update(acctemp);
				System.out.println("解冻成功");
			} else {
				System.out.println("账户不存在");
			}
		} else if (option.equals("4")) {
			startup();//退出回到一级菜单
		} else {
			System.out.println("选项输入错误，请重新输入");
		}
		showManageMenu();
		
	}

	/**
	 * 插卡二级菜单
	 */
	private void showAccountView() {
		System.out.println("1.查询余额\n2.存款\n3.取款\n4.转账\n5.修改密码\n6.退出\n请输入选项");
		String option = input.next();
		
		if (option.equals("1")) {
			System.out.println("余额为：" + account.getMoney());
		} else if (option.equals("2")) {
			System.out.println("请输入存款额");
			int inmoney = input.nextInt();
			if (inmoney < 0) {
				System.out.println("金额输入错误");
			} else {
				account.setMoney(inmoney + account.getMoney());
				Dao.update(account);
				System.out.println("存款成功");
			}
		} else if (option.equals("3")) {
			System.out.println("请输入取款额");
			int outmoney = input.nextInt();
			if (outmoney < 0 || outmoney > account.getMoney()) {
				System.out.println("金额输入错误");
			} else {
				account.setMoney(account.getMoney() - outmoney);
				Dao.update(account);
				System.out.println("取款成功");
			}
		} else if (option.equals("4")) {
			System.out.println("请输入转账账户");
			String name = input.next();
			Account inaccount = Dao.findByName(name); //不能自己给自己转
			if (inaccount!=null && !inaccount.getName().equals(account.getName())) {
				System.out.println("请输入转账额");
				int tranmoney = input.nextInt();
				if (tranmoney < 0 || tranmoney > account.getMoney()) {
					System.out.println("金额输入错误");
				} else {
					account.setMoney(account.getMoney() - tranmoney);
					inaccount.setMoney(inaccount.getMoney() + tranmoney);
					Dao.update(account);
					Dao.update(inaccount);
					System.out.println("转账成功");
				}
			} else {
				System.out.println("账户输入错误");
			}
			
		} else if (option.equals("5")) {
			
			System.out.println("请输入原始密码");
			String oldpassword = input.next();
			if (account.getPassword().equals(oldpassword)) {
				System.out.println("请输入新密码");
				String newpassword = input.next();
				account.setPassword(newpassword);
				Dao.update(account);
				System.out.println("密码修改成功");
			} else {
				System.out.println("密码输入错误"); 
				//输入错误直接跳转插卡二级菜单，不会让重复输入密码，因为没有出口
			}
		} else if (option.equals("6")) {
			startup(); //退出直接回到一级菜单
		} else {
			System.out.println("输入错误，请重新输入");
		}
		showAccountView();	
		
	}
}
