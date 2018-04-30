package com.kaishengit.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.BookService;


public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���տͻ�������
		String id = request.getParameter("id");
		BookService bookService = new BookService();
		//����service����ɾ��
			
			try {
				bookService.delBook(id);
				request.getRequestDispatcher("/list").forward(request, response);
			} catch (Exception e) {
				response.sendError(404,e.getMessage());
			} 
		
		
	}

	

}
