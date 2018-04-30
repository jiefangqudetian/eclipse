package com.kaishengit.util;

import java.util.List;

public class Page<T> {
	//总页码
	private int totalpage;
	//当前页码
	private int pageNo;
	//当前页数据
	private List<T> items;
	//每页显示数量
	private int pageSize=2;
	//起始行号
	private int start;
	//构造方法，传入书籍数量，当前页码  即可求得总页码，起始行号
	public Page(int total ,int pageNo) {
		//求得总页码
		this.totalpage = total/pageSize;
		if (total%pageSize!=0) {
			this.totalpage++;
		}
		//如果当前页码比总页码大，那么当前页码等于总页码
		if (pageNo>totalpage) {
			pageNo = totalpage;
		}
		//如果客户端传来的当前页码page比1小，那么默认显示第一页
		if (pageNo<1) {
			pageNo = 1;
		}
		//赋值给当前页码
		this.pageNo = pageNo;
		//求得起始行号
		this.start = (pageNo - 1)*pageSize;
		//pageNo>totalpage 要在 pageNo=1前面，如果在后面，当数据库中没数据时，pageNo=0,最后会导致start为负数，出现数据库查询异常
	}
	
	
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
}
