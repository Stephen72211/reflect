package com.atguigu.javaweb.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		int type = Integer.parseInt(req.getParameter("type"));
		String idCard = (String)req.getParameter("idCard");
		String examCard = (String)req.getParameter("examCard");
		String name = (String)req.getParameter("StudentName");
		String location = (String)req.getParameter("location");
		int grade = Integer.parseInt(req.getParameter("grade"));
		
		Student student = new Student(type, idCard,examCard, name, location, grade);
		
		new StudentDao().addStudent(student);
		
		resp.sendRedirect("addStudent.jsp");
		
	}
}
