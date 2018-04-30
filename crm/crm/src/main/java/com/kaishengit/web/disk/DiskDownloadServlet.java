package com.kaishengit.web.disk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.util.Config;
import com.kaishengit.web.BaseServlet;
@WebServlet("/disk/download")
public class DiskDownloadServlet extends BaseServlet{

	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("123");
		String diskId = req.getParameter("id");
		String fileName = req.getParameter("fileName");
		//System.out.println(diskId);
		//System.out.println("filename--"+fileName);
		if (StringUtils.isNumeric(diskId)) {
			Disk disk = service.findDiskById(Integer.parseInt(diskId));
			if (disk == null) {
				// 根据id查找对应的disk对象不存在报 error
				resp.sendError(404, "参数异常");
			} else {
				String filePath = Config.get("file.upload.path");
				InputStream input = new FileInputStream(new File(filePath, disk.getSaveName()));
				OutputStream out = resp.getOutputStream();
				//不是空是下载，是空的代表预览
				if (StringUtils.isNotEmpty(fileName)) {
					// 下载
					// 解决中文乱码
					fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
					// 设置MIME类型
					resp.setContentType("application/octet-stream");
					//设置文件名
					fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
					resp.addHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");//对"进行转义
				}
				IOUtils.copy(input, out);
				out.flush();
				out.close();
				input.close();
			}
		} else {
			// error
			resp.sendError(404, "参数异常");
		}
	}

}
