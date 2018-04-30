package com.kaishengit.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class Test {

	public static void main(String[] args) {
		//创建客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建post请求
		HttpPost post = new HttpPost("http://localhost/save");
		//封装post请求参数集合
		List<NameValuePair> lists = new ArrayList<>();
		lists.add(new BasicNameValuePair("name", "jack"));
		InputStream in = null;
		
		try {
			post.setEntity(new UrlEncodedFormEntity(lists));
			HttpResponse resp = httpClient.execute(post);
			int httpCode = resp.getStatusLine().getStatusCode();
			if (httpCode == 200) {
				in = resp.getEntity().getContent();
				String result = IOUtils.toString(in,"UTF-8");
				System.out.println("result:"+result);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (httpClient!=null) {
						try {
							httpClient.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*//1.创建客户端对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//2.创建httpGet对象
		HttpGet get = new HttpGet("http://www.baidu.com");
		//3.发起get请求并接收响应
		InputStream in = null;
		try {
			HttpResponse response = httpClient.execute(get);
			//4.获得响应httpCode判断httpCode的值是否为200
			int httpCode = response.getStatusLine().getStatusCode();
			if (httpCode == 200) {
				in = response.getEntity().getContent();
				String content = IOUtils.toString(in, "UTF-8");
				System.out.println("content:"+content);
			} else {
				System.out.println("访问异常,error"+httpCode);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				try {
					if (in!=null) {
					in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
						try {
							if (httpClient!=null) {
							httpClient.close();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
		}*/
	}
}
