package com.atguigu.test;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义一个 Servlet 的接口的实现类: 让开发的任何 Servlet 都继承该类,以简化开发.
 * @author stephen
 */
public abstract class MyGenericServlet implements Servlet,ServletConfig {

	/**	以下是 Servlet 接口的方法 **/
	
	@Override
	public void destroy() {}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
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
	public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException ;

	
	/**	以下是 ServletConfig 接口的方法 **/
	
	@Override
	public String getInitParameter(String name) {
		
		return servletConfig.getInitParameter(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		return servletConfig.getInitParameterNames();
	}

	@Override
	public ServletContext getServletContext() {
		return servletConfig.getServletContext();
	}

	@Override
	public String getServletName() {
		return servletConfig.getServletName();
	}

}
