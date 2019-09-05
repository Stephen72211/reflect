package com.atguigu.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 针对 HTTP 协议定义的一个 Servlet 基类
 */
public class MyHttpServlet extends MyGenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			
			if(response instanceof HttpServletResponse) {
				
				HttpServletResponse jHttpServletResponse = (HttpServletResponse) response;
			
				service(httpServletRequest, jHttpServletResponse);
			}
		}

	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		//1. 获取请求方式
			String method = request.getMethod();
		
		//2. 根据请求方式再调用对应的处理方法
		
		if("GET".equalsIgnoreCase(method)) {
			doGet(request,response);
		}
		
		if("POST".equalsIgnoreCase(method)) {
			doPost(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException,IOException{
		// TODO Auto-generated method stub
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		
	}

}
