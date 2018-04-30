package com.kaishengit.web.disk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.service.DiskService;
import com.kaishengit.util.AjaxResult;
import com.kaishengit.web.BaseServlet;
@WebServlet("/disk/new/folder")
public class DiskNewFolderServlet extends BaseServlet{
	
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pid = req.getParameter("pid");
//		System.out.println(name);
//		System.out.println("pid1"+pid);
		
		int pId = 0;
		//什么情况下可能不是数字
		//没有pid的情况，刚开始新建的时候， pId赋值0，代表根目录
		if (StringUtils.isNumeric(pid)) {
			pId = Integer.parseInt(pid);
		}
		/*req.setAttribute("sex", "男");
		System.out.println("pid2"+pid);
		forward("login", req, resp);
		System.out.println("pid3"+pid);*/
		//resp.sendRedirect("/task/addd");
		try {
			//未登录会抛空指针异常
			int accountId = getCurrAccount(req).getId();
			service.saveDiskDir(name,pId,accountId);
			sendJson(AjaxResult.success(), resp);
		} catch (NullPointerException e) {
			sendJson(AjaxResult.error("登录过期，请重新登录"), resp);
		}
		
	}

}
