package test;


import org.apache.commons.dbutils.QueryRunner;

import dao.AccountDao;
import entity.Account;

public class Daotest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AccountDao aDao = new AccountDao();
		Account acc = new Account();
		acc.setName("faker");
		aDao.update(acc);
		
		QueryRunner runner = new QueryRunner();
	}

}
