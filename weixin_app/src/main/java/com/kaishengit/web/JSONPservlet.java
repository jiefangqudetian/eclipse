package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kaishengit.entity.User;
@WebServlet("/load.js")
public class JSONPservlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		User user = new User(12, "tom", 34);
		String json = new Gson().toJson(user);
		
		PrintWriter writer = resp.getWriter();
		writer.println(m+"("+json+")");
//		writer.println(json);
		writer.flush();
		writer.close();
	}

}
