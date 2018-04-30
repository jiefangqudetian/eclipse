package com.kaishengit.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取fileName值
		String fileName = req.getParameter("fileName");
		//获取name值
		String name = req.getParameter("name");
		
		File saveDir = new File("e:/upload");
		File file = new File(saveDir,fileName);
		
		if (file.exists()) {
			//下载文件
			//获取文件输入流
			InputStream in = new FileInputStream(file);
			//获取输出流
			OutputStream out = resp.getOutputStream();
			//name不为空，下载，为空，预览
			if (StringUtils.isNotEmpty(name)) {
				//设置MIME类型
				resp.setContentType("application/octet-stream");
				//设置文件名
				name = new String(name.getBytes("UTF-8"),"ISO8859-1");
				resp.addHeader("Content-Disposition", "attachment;filename=\""+name+"\"");
				//设置文件总大小，浏览器根据所给文件大小来显示进度
				resp.setContentLengthLong(file.length());
			}
			IOUtils.copy(in,out);
			out.flush();
			out.close();
			in.close();
		} else {
			resp.sendError(404,"参数异常");
		}
	}
	
}
