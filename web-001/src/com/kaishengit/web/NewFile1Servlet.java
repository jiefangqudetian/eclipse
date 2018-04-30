package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewFile1Servlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name = (String)req.getAttribute("name");
//		String name1 = req.getParameter("name");
//		System.out.println(name);
//		System.out.println(name1);
	
//		PrintWriter out = resp.getWriter();
//		out.print("<html>");
//		out.print("<head><title>HelloServlet</title></head>");
//		out.print("<body><h4>hello,servlet</h4></body>");
//		out.print("</html>");
//		
//		out.flush();
//		out.close();
		req.setAttribute("password","123456" );
		req.getRequestDispatcher("/NewFile2").forward(req, resp);
//		req.getRequestDispatcher("/NewFile1.jsp").forward(req, resp);
	}

}
