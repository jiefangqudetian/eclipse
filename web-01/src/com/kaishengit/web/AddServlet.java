package com.kaishengit.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.BookService;


public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {
		
		//2.接收表单值
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publish = request.getParameter("publish");
		String isbn = request.getParameter("isbn");
		String num = request.getParameter("num");
		
		BookService bookService = new BookService();
		try {
			bookService.addBook(name,author,publish,isbn,num);
			
			response.sendRedirect("/list");
		} catch (Exception e) {
			request.setAttribute("name", name);
			request.setAttribute("author", author);
			request.setAttribute("publish", publish);
			request.setAttribute("isbn", isbn);
			request.setAttribute("num", num);
			request.setAttribute("message", e.getMessage());
		
			request.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(request, response);
		}
	}

}
