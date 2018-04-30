package com.kaishengit.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/upload")
public class FileuploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/fileupload.jsp").forward(req, resp);;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
		req.setCharacterEncoding("UTF-8");
		//������enctype��������Ϊmultipart/form-data֮��ᵼ���޷�ͨ��req.getparameter("name")����ȡ��ֵ
		//��������ע��MulitipartConfig
		String desc = req.getParameter("desc");
		System.out.println("desc:"+desc);
		//�����ļ��ϴ�·��
		File saveDir = new File("e:/upload");
		
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		
		File tempDir = new File("e:/temp");
		
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		
		//�жϱ��Ƿ�����enctype����Ϊmutilpart/form-data
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory itemFactory = new DiskFileItemFactory();
			//���û�������С
			itemFactory.setSizeThreshold(1024);
			//������ʱ�ļ���
			itemFactory.setRepository(tempDir);
			
			ServletFileUpload servletFileUpload = new ServletFileUpload(itemFactory);
			servletFileUpload.setSizeMax(1024*1024*10);
			System.out.println("abc");
			try {
				List<FileItem> itemList = servletFileUpload.parseRequest(req);
				for (FileItem fileItem : itemList) {
					System.out.println(123);
					if (fileItem.isFormField()) {
						//�������ͨԪ��
						System.out.println("fileName:" + fileItem.getFieldName());//��ȡ��name������ֵ
						System.out.println("getString" + fileItem.getString());//��ȡ����ͨԪ�ص�valueֵ
					} else {
						//�ļ�Ԫ��
						System.out.println("fileName:" + fileItem.getFieldName());//��ȡ���ļ�Ԫ�ص�name����ֵ
						System.out.println("name:" + fileItem.getName());//��ȡ�ϴ��ļ����ļ���
						if (StringUtils.isNotEmpty(fileItem.getName())) {//��һ���ж��������
							//����ļ�������
							InputStream in = fileItem.getInputStream();
							//�ļ������
							//�������ļ�
							String fileName = fileItem.getName();
							String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
							OutputStream out = new FileOutputStream(new File(saveDir, newFileName));
							
							byte[] buffer = new byte[1024];
							int len = -1;
							while ((len=in.read(buffer))!=-1) {
								out.write(buffer, 0, len);
							}
							out.flush();
							out.close();
							in.close();
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("enctype�����쳣");
		}
		
	}

}
