package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.dao.AccountDeptDao;
import com.kaishengit.entity.Account;
import com.kaishengit.entity.AccountDept;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.EhcacheUtil;
import com.kaishengit.util.Page;

import net.sf.ehcache.Cache;


public class AccountService {
	
	AccountDao accountDao = new AccountDao();
	AccountDeptDao accountDeptDao = new AccountDeptDao();
	EhcacheUtil ehcacheUtil = new EhcacheUtil("account");
	
	
	public Account login(String username, String password) {
		Account account = accountDao.findByName(username);
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));

		if (account != null && md5Password.equals(account.getPassword())) {
			return account;
		} else {
			System.out.println("你好");
			throw new ServiceException("用户名或者密码错误");
		}
	}

	public void saveAccount(String userName, String password, String mobile, String[] deptIds) throws ServiceException {
		// TODO Auto-generated method stub
		// 校验手机号码是否存在
		Account account = accountDao.findByMobile(mobile);
		if (account != null) {
			throw new ServiceException("该电话号码已经存在");
		}
		// 新增account数据,id自动生成不用设置
		account = new Account();
		account.setUsername(userName);
		// 密码加盐保存
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		account.setPassword(md5Password);
		account.setMobile(mobile);
		account.setUpdateTime(new Timestamp(System.currentTimeMillis()));// 获得日期时间
		// 获得保存account后自动生成的id
		int accountId = accountDao.save(account);
		
		// 新增员工和部门的对应关系方法1
		/*
		 * for(String deptId: deptIds) { AccountDept accountDept = new AccountDept();
		 * accountDept.setAccountId(accountId);
		 * accountDept.setDeptId(Integer.parseInt(deptId));
		 * accountDeptDao.save(accountDept); }
		 */
		// 新增员工和部门的对应关系方法2
		List<AccountDept> accountDeptLists = new ArrayList<>();
		for (String deptId : deptIds) {
			AccountDept accountDept = new AccountDept();
			accountDept.setAccountId(accountId);
			accountDept.setDeptId(Integer.parseInt(deptId));
			accountDeptLists.add(accountDept);
		}
		accountDeptDao.save(accountDeptLists);
	}

	public Page<Account> findAccountByPage(String deptId, int pageNo) {
		int count = accountDao.count(deptId);
		Page<Account> page = new Page<>(count, pageNo);
		List<Account> accountList = accountDao.findByPage(deptId,page.getStart(),page.getPageSize());
		page.setItems(accountList);
		return page;
	}

	/**
	 * 获取所有员工列表
	 * @return员工对象集合
	 */
	public List<Account> findAllAccount() {
		List<Account> accountList = (ArrayList<Account>)ehcacheUtil.getValue("accountList");
		if (accountList==null) {
			accountList=accountDao.findAll();
			ehcacheUtil.setCache("accountList", accountList);
		}
		return accountList;
	}

	public void deleteAccount(String accId) {
		accountDao.deleteAccount(accId);
		
	}

	public void editAccount(String userName, String password, String mobile, String[] deptIds, String editId) {
		// 校验手机号码是否存在
		Account account = accountDao.findByMobile(mobile);
		if (account != null) {
			throw new ServiceException("该电话号码已经存在");
		}
		
		// 密码加盐保存
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		
		accountDao.edit(userName,md5Password,mobile,updateTime,editId);
		accountDeptDao.edit(deptIds,editId);
		
	}

	/**修改密码
	 * @param accountId 
	 * @param oldPassword
	 * @param newPassword
	 * @param confirm
	 */
	public void changePassword(int accountId, String oldPassword, String newPassword, String confirm) {
		// TODO Auto-generated method stub
		Account account = accountDao.findById(accountId);
		String oldPass = DigestUtils.md5Hex(oldPassword + Config.get("user.password.salt"));
		if ( !oldPass.equals(account.getPassword())) {
			throw new ServiceException("原始密码输入错误");
		}
		if (!newPassword.equals(confirm)) {
			throw new ServiceException("两次密码输入不一致");
		}
		
		String newPass = DigestUtils.md5Hex(newPassword + Config.get("user.password.salt"));
		account.setPassword(newPass);
		accountDao.updatePass(account);
	}
}
