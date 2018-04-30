package atm;

import java.util.List;
import java.util.Scanner;

import dao.AccountDao;
import entity.Account;

public class Atm {

	AccountDao Dao = new AccountDao();
	Scanner input = new Scanner(System.in);
	String pname;
	public void startup() {
		System.out.println("1.开户\n2.插卡\n3.系统管理\n4.退出\n请输入选项");
		String option = input.next();

		if (option.equals("1")) {

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
		} else if (option.equals("2")) {

			System.out.println("请输入账户");
			pname = input.next();
			System.out.println("请输入密码");
			String password = input.next();
			Account acctemp = Dao.findByNameAndPassword(pname, password);
			if (acctemp != null) {
				if (acctemp.getState().equals("正常")) {
					System.out.println("登录成功");
					showAccountView();
					
				} else {
					System.out.println("账户被冻结，请联系管理员");
				}
			} else {
				System.out.println("账户或密码输入错误");
			}
		} else if (option.equals("3")) {

			System.out.println("请输入管理员密码");
			String password = input.next();
			if (password.equals("123456")) {
				showMangageMenu();
			} else {
				System.out.println("密码输入错误");
			}
			
		} else if (option.equals("4")) {
			System.exit(0);
		} else {
			System.out.println("选项输入错误，请重新输入");
		}

		startup();
	}

	private void showMangageMenu() {
		System.out.println("1.查看账户状态\n2.冻结账户\n3.解冻账户\n4.退出\n请输入选项");
		String option = input.next();
		if (option.equals("1")) {
			List<Account> list = Dao.findAll();
			for (Account account : list) {
				System.out.println("账户名："+account.getName()+ "状态:"+account.getState());
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
			startup();
		} else {
			System.out.println("选项输入错误，请重新输入");
		}
		showMangageMenu();
		
	}

	private void showAccountView() {
		System.out.println("1.查询余额\n2.存款\n3.取款\n4.转账\n5.修改密码\n6.退出\n请输入选项");
		String option = input.next();
		
		if (option.equals("1")) {
			Account acctemp = Dao.findByName(pname);
			System.out.println("余额为：" + acctemp.getMoney());
		} else if (option.equals("2")) {
			System.out.println("请输入存款额");
			int inmoney = input.nextInt();
			if (inmoney < 0) {
				System.out.println("金额输入错误");
			} else {
				Account acctemp = Dao.findByName(pname);
				acctemp.setMoney(inmoney + acctemp.getMoney());
				Dao.update(acctemp);
				System.out.println("存款成功");
			}
		} else if (option.equals("3")) {
			Account acctemp = Dao.findByName(pname);
			System.out.println("请输入取款额");
			int outmoney = input.nextInt();
			if (outmoney < 0 || outmoney > acctemp.getMoney()) {
				System.out.println("金额输入错误");
			} else {
				acctemp.setMoney(acctemp.getMoney() - outmoney);
				Dao.update(acctemp);
				System.out.println("取款成功");
			}
		} else if (option.equals("4")) {
			System.out.println("请输入转账账户");
			String name = input.next();
			Account acctemp = Dao.findByName(pname);
			Account inaccount = Dao.findByName(name);
			if (inaccount!=null && !inaccount.getName().equals(pname)) {
				System.out.println("请输入转账额");
				int tranmoney = input.nextInt();
				if (tranmoney < 0 || tranmoney > acctemp.getMoney()) {
					System.out.println("金额输入错误");
				} else {
					acctemp.setMoney(acctemp.getMoney() - tranmoney);
					inaccount.setMoney(inaccount.getMoney() + tranmoney);
					Dao.update(acctemp);
					Dao.update(inaccount);
					System.out.println("转账成功");
				}
			} else {
				System.out.println("账户输入错误");

			}
			
		} else if (option.equals("5")) {
			
			System.out.println("请输入原始密码");
			String oldpassword = input.next();
			Account acctemp = Dao.findByNameAndPassword(pname, oldpassword);
			if (acctemp!=null) {
				System.out.println("请输入新密码");
				String newpassword = input.next();
				acctemp.setPassword(newpassword);
				Dao.update(acctemp);
				System.out.println("密码修改成功");
			} else {
				System.out.println("密码输入错误");
			}
		} else if (option.equals("6")) {
			startup();
		} else {
			System.out.println("输入错误，请重新输入");
		}
		showAccountView();	
		
	}
}
