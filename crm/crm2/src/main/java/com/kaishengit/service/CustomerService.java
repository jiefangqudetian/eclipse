package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.dao.CustomerDao;
import com.kaishengit.dao.SaleDao;
import com.kaishengit.dao.SaleRecordDao;
import com.kaishengit.entity.Account;
import com.kaishengit.entity.Customer;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class CustomerService {
	CustomerDao custDao = new CustomerDao();
	AccountDao accDao = new AccountDao();
	SaleDao saleDao = new SaleDao();
	SaleRecordDao saleRecordDao = new SaleRecordDao();
	/**
	 * 新增客户是获得所有客户来源列表
	 * @return
	 */
	public List<String> findAllSources() {
		 String sourceStr = Config.get("customer.source");
		 String[] sourceArray = sourceStr.split(",");
		 return Arrays.asList(sourceArray);
	}
	
	/**
	 * 新增客户是获得所有客户行业列表
	 * @return
	 */
	public List<String> findAllTrades() {
		 String tradeStr = Config.get("customer.trade");
		 String[] tradeArray = tradeStr.split(",");
		 return Arrays.asList(tradeArray);
	}

	/**
	 * 
	 * 新增当前登录员工的客户信息
	 * @param custname
	 * @param sex
	 * @param jobtitle
	 * @param mobile
	 * @param source
	 * @param trade
	 * @param level
	 * @param mark
	 * @param id 当前登录员工的accountId
	 */
	public void addMyCustomer(String custname, String sex, String jobTitle,String address, String mobile, String source, String trade,
			String level, String mark, int accountId) {
		
		Customer customer = new Customer(custname, sex, jobTitle,address, mobile, trade, source, level, mark, accountId);
		customer.setReminder("员工添加");
		
		custDao.add(customer);
		
	}

	/**
	 * 根据accountId 和 pageNo查找对应的customer集合
	 * @param id
	 * @param pageNo
	 * @return 
	 */
	public Page<Customer> findMyCustomerByPage(int accountId, int pageNo) {
		int count = custDao.count(accountId);
		Page<Customer> page = new Page<>(count, pageNo);
		List<Customer> custList = custDao.findCustomerListByPage(accountId, page.getStart(), page.getPageSize());
		page.setItems(custList);
		return page;
	}

	public Customer findCustomerById(String custId, int accountId) {
		Customer cust = checkCustomer(custId,accountId);
		return cust;
	}

	private Customer checkCustomer(String custId, int accountId) {
		// 1.校验custId是否是数字
		if (!StringUtils.isNumeric(custId)) {
			throw new ServiceException("参数异常");
		}
		// 2.根据custId查找对应的对象，如果该对象不存在，在抛出异常
		Customer cust = custDao.findById(Integer.parseInt(custId));
		if (cust == null) {
			throw new ServiceException("参数异常");
		}
		// 3.校验该客户是否为当前登录员工的客户
		if (cust.getAccountId()!=0 &&cust.getAccountId()!=accountId) {
			throw new ServiceException("拒绝访问");
		}
		return cust;
	}

	/**
	 * 根据这些参数，在数据库更新customer表，先根据custId找到customer实体类对象
	 * 设置customer对象属性，根据customer对象更新数据库
	 * @param custId custname sex jobtitle address mobile source trade level mark
	 */
	public void edit(String custId, String custname, String sex, String jobtitle, String address, String mobile,
			String source, String trade, String level, String mark) {

		Customer cust = custDao.findById(Integer.parseInt(custId));
		cust.setCustName(custname);
		cust.setSex(sex);
		cust.setJobTitle(jobtitle);
		cust.setAddress(address);
		cust.setMobile(mobile);
		cust.setSource(source);
		cust.setTrade(trade);
		cust.setLevel(level);
		cust.setMark(mark);
		
		//修改更新时间
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		custDao.update(cust);
		
	}

	/**
	 * 根据custId accountId 查找customer对象
	 * 如果custId合法，查找到的customer对象的accountId与参数传入accountId相符合
	 * 则返回查找到的customer对象
	 */
	public Customer getCustomerById(String custId, int accountId) {
		//  1.校验custId是否合法
		if(!StringUtils.isNumeric(custId)) {
			throw new ServiceException("参数异常");
		}
		
		//  2.根据custId查找对应的对象，如果该对象不存在，在抛出异常
		Customer cust = custDao.findById(Integer.parseInt(custId));
		if(cust == null) {
			throw new ServiceException("参数异常");
		}
		//  3.校验该客户是否为当前登录员工的客户 并校验该客户是否是公海客户
		//只有该客户的员工不是当前登录员工且该客户也不是公海客户时抛异常
		if(cust.getAccountId() != accountId && cust.getAccountId() !=0) {
			throw new ForbiddenException("拒绝访问");
		}	
		
		return cust;
	}


	/**
	 * 把custId对应的客户放入公海
	 * @param accountId
	 */
	public void toPublicByCustId(String custId, int accountId) {
		Customer customer= checkCustomer(custId, accountId);
		
		Account account = accDao.findById(accountId);
		
		customer.setAccountId(Config.PUBLIC_ID);
		customer.setReminder(customer.getReminder() + "," + account.getUsername() +"放入公海");
		custDao.update(customer);
		
	}

	/**
	 * 转交客户
	 * @param accountId
	 * @param toAccountId
	 */
	public void transByCustId(String custId, int accountId, String toAccountId) {
		Customer customer= checkCustomer(custId, accountId);
		// 获得原来所属的account对象
		Account account = accDao.findById(accountId);
		// 判断toAccountId是否存在
		Account toAccount = checkAccount(toAccountId);

		// 如果存在转交给toAccountId
		customer.setAccountId(toAccount.getId());
		customer.setReminder(customer.getReminder() + "," + account.getUsername() +"转交");
		custDao.update(customer);
		
	}

	private Account checkAccount(String toAccountId) {
		//  1.校验custId是否合法
		if(!StringUtils.isNumeric(toAccountId)) {
			throw new ServiceException("参数异常");
		}
		
		//  2.根据custId查找对应的对象，如果该对象不存在，在抛出异常
		Account acc = accDao.findById(Integer.parseInt(toAccountId));
		if(acc == null) {
			throw new ServiceException("参数异常");
		}
		
		return acc;
	}

	public void confirmByCustIdAndAccountId(String custId, int accountId) {
		Customer customer = custDao.findById(Integer.parseInt(custId));
		customer.setAccountId(accountId);
		Account account = accDao.findById(accountId);
		customer.setReminder(customer.getReminder() + "," + account.getUsername() +"认领");
		custDao.update(customer);
	}

	/**根据客户id查找客户
	 * @param custId
	 * @return
	 */
	
	public Customer getCustomerById(String custId) {
		Customer customer = custDao.findById(Integer.parseInt(custId));
		return customer;
	}

	public List<Map<String, Object>> countCustomerLevel() {
		return custDao.countLevel();
	}

	/**根据客户ID删除客户，三表联删，t_customer sale_chance sale_chance_record
	 * @param custId
	 */
	public void delCustById(String custId) {
		int id = Integer.parseInt(custId);
		List<Integer> saleId = custDao.findSaleByCustId(id);
		custDao.delById(id);
		for (Integer integer : saleId) {
			saleDao.delById(integer);
			saleRecordDao.delBySaleId(integer);
		}
	}

	

}
