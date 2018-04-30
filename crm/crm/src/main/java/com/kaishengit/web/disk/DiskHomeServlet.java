package com.kaishengit.web.disk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/disk/home")
public class DiskHomeServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	DiskService service = new DiskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		int pId = 0;
		if (StringUtils.isNumeric(pid)) {
			pId = Integer.parseInt(pid);
		}
		
		//查询pId对应的disk集合
		List<Disk> diskList = service.findDiskListByPId(pId);
		req.setAttribute("diskList", diskList);
		// 获得pid对应的文件夹本身
		
		if (pId!=0) {
			Disk disk = service.findDiskById(pId);
			req.setAttribute("disk", disk);
		}
		
		forward("disk/home", req, resp);
	}

}
