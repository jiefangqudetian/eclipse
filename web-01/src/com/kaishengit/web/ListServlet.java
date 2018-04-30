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
			//如果url传过来的p是数字字符串，那么将其转化为对应数字赋值给pageNo,如果不是数字字符串，那么默认等于1
			int pageNo = 1;
			if (StringUtils.isNumeric(p)) {
				pageNo = Integer.parseInt(p);
			}
			//
			BookService bookService = new BookService();
			Page<Book> page = bookService.findBookByPage(pageNo); 
			//将page对象键值对存入request中，请求转发传回前端页面
			req.setAttribute("page", page);
			req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
			
		
	}

}
