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
		System.out.println("1.����\n2.�忨\n3.ϵͳ����\n4.�˳�\n������ѡ��");
		String option = input.next();

		if (option.equals("1")) {

			System.out.println("�������˻�");
			String name = input.next();

			if (Dao.findByName(name) != null) {
				System.out.println("�˻��Ѵ���");
			} else {
				System.out.println("����������");
				String password = input.next();
				Account acctemp = new Account();
				acctemp.setName(name);
				acctemp.setPassword(password);
				Dao.save(acctemp);
				System.out.println("�����ɹ�");
			}
		} else if (option.equals("2")) {

			System.out.println("�������˻�");
			pname = input.next();
			System.out.println("����������");
			String password = input.next();
			Account acctemp = Dao.findByNameAndPassword(pname, password);
			if (acctemp != null) {
				if (acctemp.getState().equals("����")) {
					System.out.println("��¼�ɹ�");
					showAccountView();
					
				} else {
					System.out.println("�˻������ᣬ����ϵ����Ա");
				}
			} else {
				System.out.println("�˻��������������");
			}
		} else if (option.equals("3")) {

			System.out.println("���������Ա����");
			String password = input.next();
			if (password.equals("123456")) {
				showMangageMenu();
			} else {
				System.out.println("�����������");
			}
			
		} else if (option.equals("4")) {
			System.exit(0);
		} else {
			System.out.println("ѡ�������������������");
		}

		startup();
	}

	private void showMangageMenu() {
		System.out.println("1.�鿴�˻�״̬\n2.�����˻�\n3.�ⶳ�˻�\n4.�˳�\n������ѡ��");
		String option = input.next();
		if (option.equals("1")) {
			List<Account> list = Dao.findAll();
			for (Account account : list) {
				System.out.println("�˻�����"+account.getName()+ "״̬:"+account.getState());
			}
		} else if (option.equals("2")) {
			System.out.println("������Ҫ�����˻�");
			String name = input.next();
			Account acctemp = Dao.findByName(name) ;
			if (acctemp!=null) {
				acctemp.setState("�쳣");
				Dao.update(acctemp);
				System.out.println("����ɹ�");
			} else {
				System.out.println("�˻�������");
			}
		} else if (option.equals("3")) {
			System.out.println("������Ҫ�ⶳ�˻�");
			String name = input.next();
			Account acctemp = Dao.findByName(name) ;
			if (acctemp!=null) {
				acctemp.setState("����");
				Dao.update(acctemp);
				System.out.println("�ⶳ�ɹ�");
			} else {
				System.out.println("�˻�������");
			}
		} else if (option.equals("4")) {
			startup();
		} else {
			System.out.println("ѡ�������������������");
		}
		showMangageMenu();
		
	}

	private void showAccountView() {
		System.out.println("1.��ѯ���\n2.���\n3.ȡ��\n4.ת��\n5.�޸�����\n6.�˳�\n������ѡ��");
		String option = input.next();
		
		if (option.equals("1")) {
			Account acctemp = Dao.findByName(pname);
			System.out.println("���Ϊ��" + acctemp.getMoney());
		} else if (option.equals("2")) {
			System.out.println("���������");
			int inmoney = input.nextInt();
			if (inmoney < 0) {
				System.out.println("����������");
			} else {
				Account acctemp = Dao.findByName(pname);
				acctemp.setMoney(inmoney + acctemp.getMoney());
				Dao.update(acctemp);
				System.out.println("���ɹ�");
			}
		} else if (option.equals("3")) {
			Account acctemp = Dao.findByName(pname);
			System.out.println("������ȡ���");
			int outmoney = input.nextInt();
			if (outmoney < 0 || outmoney > acctemp.getMoney()) {
				System.out.println("����������");
			} else {
				acctemp.setMoney(acctemp.getMoney() - outmoney);
				Dao.update(acctemp);
				System.out.println("ȡ��ɹ�");
			}
		} else if (option.equals("4")) {
			System.out.println("������ת���˻�");
			String name = input.next();
			Account acctemp = Dao.findByName(pname);
			Account inaccount = Dao.findByName(name);
			if (inaccount!=null && !inaccount.getName().equals(pname)) {
				System.out.println("������ת�˶�");
				int tranmoney = input.nextInt();
				if (tranmoney < 0 || tranmoney > acctemp.getMoney()) {
					System.out.println("����������");
				} else {
					acctemp.setMoney(acctemp.getMoney() - tranmoney);
					inaccount.setMoney(inaccount.getMoney() + tranmoney);
					Dao.update(acctemp);
					Dao.update(inaccount);
					System.out.println("ת�˳ɹ�");
				}
			} else {
				System.out.println("�˻��������");

			}
			
		} else if (option.equals("5")) {
			
			System.out.println("������ԭʼ����");
			String oldpassword = input.next();
			Account acctemp = Dao.findByNameAndPassword(pname, oldpassword);
			if (acctemp!=null) {
				System.out.println("������������");
				String newpassword = input.next();
				acctemp.setPassword(newpassword);
				Dao.update(acctemp);
				System.out.println("�����޸ĳɹ�");
			} else {
				System.out.println("�����������");
			}
		} else if (option.equals("6")) {
			startup();
		} else {
			System.out.println("�����������������");
		}
		showAccountView();	
		
	}
}
