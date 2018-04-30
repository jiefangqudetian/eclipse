 package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Book;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Page;

public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String p = req.getParameter("p");
			//���url��������p�������ַ�������ô����ת��Ϊ��Ӧ���ָ�ֵ��pageNo,������������ַ�������ôĬ�ϵ���1
			int pageNo = 1;
			if (StringUtils.isNumeric(p)) {
				pageNo = Integer.parseInt(p);
			}
			//
			BookService bookService = new BookService();
			Page<Book> page = bookService.findBookByPage(pageNo); 
			//��page�����ֵ�Դ���request�У�����ת������ǰ��ҳ��
			req.setAttribute("page", page);
			req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
			
		
	}

}
