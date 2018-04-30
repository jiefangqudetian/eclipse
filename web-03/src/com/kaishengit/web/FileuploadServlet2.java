package com.kaishengit.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@WebServlet("/upload2")
@MultipartConfig
public class FileuploadServlet2 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/fileupload2.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//getParameter根据input输入框的name属性值获得value值
		String desc = req.getParameter("desc");
		System.out.println(desc);
		//getPart根据input输入框的name属性值获得part对象
		Part part = req.getPart("file1");
		System.out.println(part.getName());
		System.out.println(part.getSize());
		System.out.println(FileUtils.byteCountToDisplaySize(part.getSize()));
		System.out.println(part.getSubmittedFileName());
		System.out.println(part.getContentType());
		
		//获得输入流
		InputStream in = part.getInputStream();
		//获得输出流
		File saveDir = new File("e:/upload");//？如果已存在文件呢
		//改名
		String fileName = part.getSubmittedFileName();
		String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		//创建输出流
		OutputStream out = new FileOutputStream(new File(saveDir, newFileName));
		IOUtils.copy(in, out);
		
		out.flush();
		out.close();
		in.close();
		
	}

}
