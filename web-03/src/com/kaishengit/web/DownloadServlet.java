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
		//��ȡfileNameֵ
		String fileName = req.getParameter("fileName");
		//��ȡnameֵ
		String name = req.getParameter("name");
		
		File saveDir = new File("e:/upload");
		File file = new File(saveDir,fileName);
		
		if (file.exists()) {
			//�����ļ�
			//��ȡ�ļ�������
			InputStream in = new FileInputStream(file);
			//��ȡ�����
			OutputStream out = resp.getOutputStream();
			//name��Ϊ�գ����أ�Ϊ�գ�Ԥ��
			if (StringUtils.isNotEmpty(name)) {
				//����MIME����
				resp.setContentType("application/octet-stream");
				//�����ļ���
				name = new String(name.getBytes("UTF-8"),"ISO8859-1");
				resp.addHeader("Content-Disposition", "attachment;filename=\""+name+"\"");
				//�����ļ��ܴ�С����������������ļ���С����ʾ����
				resp.setContentLengthLong(file.length());
			}
			IOUtils.copy(in,out);
			out.flush();
			out.close();
			in.close();
		} else {
			resp.sendError(404,"�����쳣");
		}
	}
	
}
