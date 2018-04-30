package com.kaishengit.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;

public class LoginFilter extends AbstractFilter {
	
	List<String> uriList = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String validate = config.getInitParameter("validate"); // /account,/cust,/sale
		String[] validateUri = validate.split(","); 
		uriList = Arrays.asList(validateUri); // 把数组转换成集合  [/account,/cust]
	}
	
	private boolean validate(String uri) {
		// 1.排除/
		if(uriList == null) {
			return false;
		}
		
		for(String str : uriList) {
			if(uri.startsWith(str)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		// 1.获得请求路径
		String uri = req.getRequestURI();
		// 如果uri是以uriList中的某个元素开头，那么该uri需要经过过滤，否则直接通过  eg: /account/list  /cust/add
		if(validate(uri)) {
			// 校验是否已经登录
			HttpSession session = req.getSession();
			Account account = (Account)session.getAttribute("account");
			// 如果已经登录，直接通过
			if(account != null) {
				chain.doFilter(req, resp);
			} else {
				// 如果未登录，给uri加上参数，跳转登录页面
				uri = getUriWithParam(uri, req);
				uri = URLEncoder.encode(uri,"utf-8");
				resp.sendRedirect("/login?callback=" + uri);
			}
			
		} else {
			chain.doFilter(req, resp);
		}

	}
	
	/**获得带参数的uri
	 * @param uri
	 * @param req
	 * @return
	 */
	private String  getUriWithParam(String uri,HttpServletRequest req) {
		Map<String, String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		Iterator<String> it = keys.iterator();
		if (it.hasNext()) {
			uri += "?";
			while (it.hasNext()) {
				String key = it.next();
				String values[] = params.get(key);
				for (String value : values) {
					String param =  key + "=" +value + "&";
					uri = uri + param;
				}
				
			}
			uri = uri.substring(0, uri.length()-1);
		}
		
		
		return uri;
	}

}
