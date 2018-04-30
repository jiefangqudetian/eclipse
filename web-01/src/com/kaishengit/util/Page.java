package com.kaishengit.util;

import java.util.List;

public class Page<T> {
	//��ҳ��
	private int totalpage;
	//��ǰҳ��
	private int pageNo;
	//��ǰҳ����
	private List<T> items;
	//ÿҳ��ʾ����
	private int pageSize=2;
	//��ʼ�к�
	private int start;
	//���췽���������鼮��������ǰҳ��  ���������ҳ�룬��ʼ�к�
	public Page(int total ,int pageNo) {
		//�����ҳ��
		this.totalpage = total/pageSize;
		if (total%pageSize!=0) {
			this.totalpage++;
		}
		//�����ǰҳ�����ҳ�����ô��ǰҳ�������ҳ��
		if (pageNo>totalpage) {
			pageNo = totalpage;
		}
		//����ͻ��˴����ĵ�ǰҳ��page��1С����ôĬ����ʾ��һҳ
		if (pageNo<1) {
			pageNo = 1;
		}
		//��ֵ����ǰҳ��
		this.pageNo = pageNo;
		//�����ʼ�к�
		this.start = (pageNo - 1)*pageSize;
		//pageNo>totalpage Ҫ�� pageNo=1ǰ�棬����ں��棬�����ݿ���û����ʱ��pageNo=0,���ᵼ��startΪ�������������ݿ��ѯ�쳣
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
