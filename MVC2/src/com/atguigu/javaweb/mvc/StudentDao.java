package com.atguigu.javaweb.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

	
	public List<Student> getAll(){
		
		List<Student>  students= new ArrayList<Student>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			String driverClass= "com.mysql.jdbc.Driver";
			
			String url = "jdbc:mysql://192.168.3.26:3306/ajax";
			String user = "root";
			String password = "1230";
			
			Class.forName(driverClass);
			
			connection = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT flow_id, type, id_card, exam_card, student_name, location, grade "
					+ "FROM examstudent";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			
			
			while(resultSet.next()){

				int flow_id = resultSet.getInt(1);
				int type = resultSet.getInt(2);
				String id_card = resultSet.getString(3);
				String exam_card = resultSet.getString(4);
				String student_name= resultSet.getString(5);
				String location = resultSet.getString(6);
				int grade = resultSet.getInt(7);
				
				Student student = new Student(flow_id, type, id_card, exam_card, student_name, location, grade);
				
				students.add(student);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null){
					resultSet.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(preparedStatement != null){
					preparedStatement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				if(connection != null){
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		
		return students;
	}
	
}
