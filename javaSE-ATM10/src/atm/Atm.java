package atm;

import java.util.List;
import java.util.Scanner;

import dao.AccountDao;
import entity.Account;

public class Atm {

	//����Dao�еķ����� findbyname findall save update 
	AccountDao Dao = new AccountDao();
	Account account = new Account();  //����ϵͳ��Ҫһ����ǰ�˻� ��һ��Dao
	Scanner input = new Scanner(System.in);
	public void startup() {
		System.out.println("1.����\n2.�忨\n3.ϵͳ����\n4.�˳�\n������ѡ��");
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
			System.out.println("ѡ�������������������");
		}
		startup();
	}

	/**
	 * 3.ϵͳ����
	 */
	private void manageSystem() {
		// TODO Auto-generated method stub
		System.out.println("���������Ա����");
		String password = input.next();
		if (password.equals("123456")) {
			showManageMenu();
		} else {
			System.out.println("�����������");
		}
	}

	/**
	 * 2.�忨
	 */
	private void insertAccount() {
		// TODO Auto-generated method stub
		System.out.println("�������˻�");
		String name = input.next();
		System.out.println("����������");
		String password = input.next();
		account = Dao.findByName(name); //�����˻���Ȼ��ֵ��acctemp�����ж�acctemp���������������
		if (account != null && account.getPassword().equals(password)) {
			if (account.getState().equals("����")) {
				System.out.println("��¼�ɹ�");
				showAccountView();
			} else {
				System.out.println("�˻������ᣬ����ϵ����Ա");
			}
		} else {
			System.out.println("�˻��������������");
		}
	}

	/**
	 * 1.����
	 */
	private void createAccount() {
		// TODO Auto-generated method stub
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
	}

	/**
	 * ����Ա�����˵�
	 */
	private void showManageMenu() {
		System.out.println("1.�鿴�˻�״̬\n2.�����˻�\n3.�ⶳ�˻�\n4.�˳�\n������ѡ��");
		String option = input.next();
		if (option.equals("1")) {
			List<Account> list = Dao.findAll();
			System.out.println("�˻�����\t\t״̬��" ); //�Ʊ�λ��ȫ����
			for (Account account : list) {
				System.out.println(account.getName()+"\t\t"+account.getState());
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
			startup();//�˳��ص�һ���˵�
		} else {
			System.out.println("ѡ�������������������");
		}
		showManageMenu();
		
	}

	/**
	 * �忨�����˵�
	 */
	private void showAccountView() {
		System.out.println("1.��ѯ���\n2.���\n3.ȡ��\n4.ת��\n5.�޸�����\n6.�˳�\n������ѡ��");
		String option = input.next();
		
		if (option.equals("1")) {
			System.out.println("���Ϊ��" + account.getMoney());
		} else if (option.equals("2")) {
			System.out.println("���������");
			int inmoney = input.nextInt();
			if (inmoney < 0) {
				System.out.println("����������");
			} else {
				account.setMoney(inmoney + account.getMoney());
				Dao.update(account);
				System.out.println("���ɹ�");
			}
		} else if (option.equals("3")) {
			System.out.println("������ȡ���");
			int outmoney = input.nextInt();
			if (outmoney < 0 || outmoney > account.getMoney()) {
				System.out.println("����������");
			} else {
				account.setMoney(account.getMoney() - outmoney);
				Dao.update(account);
				System.out.println("ȡ��ɹ�");
			}
		} else if (option.equals("4")) {
			System.out.println("������ת���˻�");
			String name = input.next();
			Account inaccount = Dao.findByName(name); //�����Լ����Լ�ת
			if (inaccount!=null && !inaccount.getName().equals(account.getName())) {
				System.out.println("������ת�˶�");
				int tranmoney = input.nextInt();
				if (tranmoney < 0 || tranmoney > account.getMoney()) {
					System.out.println("����������");
				} else {
					account.setMoney(account.getMoney() - tranmoney);
					inaccount.setMoney(inaccount.getMoney() + tranmoney);
					Dao.update(account);
					Dao.update(inaccount);
					System.out.println("ת�˳ɹ�");
				}
			} else {
				System.out.println("�˻��������");
			}
			
		} else if (option.equals("5")) {
			
			System.out.println("������ԭʼ����");
			String oldpassword = input.next();
			if (account.getPassword().equals(oldpassword)) {
				System.out.println("������������");
				String newpassword = input.next();
				account.setPassword(newpassword);
				Dao.update(account);
				System.out.println("�����޸ĳɹ�");
			} else {
				System.out.println("�����������"); 
				//�������ֱ����ת�忨�����˵����������ظ��������룬��Ϊû�г���
			}
		} else if (option.equals("6")) {
			startup(); //�˳�ֱ�ӻص�һ���˵�
		} else {
			System.out.println("�����������������");
		}
		showAccountView();	
		
	}
}
