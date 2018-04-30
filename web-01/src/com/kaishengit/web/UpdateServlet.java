package com.kaishengit.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Book;
import com.kaishengit.service.BookService;

public class UpdateServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			String id = request.getParameter("id");
			
			BookService bookService = new BookService();
			Book book = bookService.findByBook(id);
			
			if (book!=null) {
				//请求转发到update.jsp
				request.setAttribute("book", book);
				request.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(request,response);
			} else {
				response.sendError(404,"参数异常");//新方法
			}
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String publish = req.getParameter("publish");
		String isbn = req.getParameter("isbn");
		String count = req.getParameter("count");
		String currCount = req.getParameter("currCount");
		
		BookService bookService = new BookService();
		
		bookService.updateBook(id,name,author,publish,isbn,count,currCount);
		resp.sendRedirect("/list");
	
	}

}
