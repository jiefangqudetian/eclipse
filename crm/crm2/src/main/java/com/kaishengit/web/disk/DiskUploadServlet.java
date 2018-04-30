package com.kaishengit.web.disk;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import com.kaishengit.entity.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/disk/upload")
@MultipartConfig
public class DiskUploadServlet extends BaseServlet{

	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("file");
		//获得文件上传的二进制流
		InputStream input = part.getInputStream();
		//获得文件上传的大小
		long fileSize = part.getSize();
		// 获得文件上传的原始名称
		String name = req.getParameter("name");// part.getSubmittedFileName(); error:tomcat7不支持
		// 从formData中获得pid 
		String pid = req.getParameter("pid");
		//从formData中获得pid
		String md5 = req.getParameter("fileMd5");
		//获得accountId
		int accountId = getCurrAccount(req).getId();
		//获得文件保存名
		String saveName = UUID.randomUUID()+name.substring(name.lastIndexOf(".")); 
		
		Disk disk = new Disk();
		disk.setSaveName(saveName);
		disk.setName(name);
		disk.setFileSize(FileUtils.byteCountToDisplaySize(fileSize));
		disk.setpId(Integer.parseInt(pid));
		disk.setAccountId(accountId);
		disk.setMd5(md5);
		disk.setDownloadCount(Disk.INIT_DOWNLOAD_COUNT); //初始下载次数
		disk.setType(Disk.DISK_TYPE_FILE);
		try {
			service.saveNewFile(disk);
			service.uploadFile(input,saveName);
			sendJson(AjaxResult.success(), resp);
		} catch (NullPointerException e) {
			sendJson(AjaxResult.error("登录信息已过期，请重新登录"), resp);
		}
	}

}
