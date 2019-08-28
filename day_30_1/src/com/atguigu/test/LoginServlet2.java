package com.atguigu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet2 implements Servlet{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}


	
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private ServletConfig servletConfig; 
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig = servletConfig;
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		//1. 获取请求参数

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		//2. 获取当前 WEB 应用的初始化参数
		//   需要使用 ServletContext 对象.
		
		ServletContext servletContext = servletConfig.getServletContext();
		String initUser = servletContext.getInitParameter("user");
		String initPassword = servletContext.getInitParameter("password");
		
		
		PrintWriter out = response.getWriter();
		//3. 比对
		//4. 输出结果
		if((initUser.equals(username)) && (initPassword.equals(password))) {
			out.print("Hello: " +  username);
		}else {
			out.print("Sorry: " +  username);
		}
		
	}

}
