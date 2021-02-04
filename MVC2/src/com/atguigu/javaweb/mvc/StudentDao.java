package com.atguigu.javaweb.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.javaweb.Dao.DaoTest;

public class StudentDao {
	
	DaoTest daoTest = new DaoTest();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	public void delete(Integer flowId){
		
		String sql = "DELETE FROM examstudent WHERE flow_id = ?";
		try {
			connection = daoTest.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, flowId);
			preparedStatement.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addStudent(Student student){
		

	
		
		Integer type;
		String id_card;
		String exam_card;
		String student_name;
		String location;
		Integer grade;
		
		
		type = student.getType();
		id_card = student.getId_card();
		exam_card = student.getExam_card();
		student_name = student.getStudent_name();
		location = student.getLocation();
		grade = student.getGrade();
		
		String sql = "INSERT INTO examstudent(type,id_card,exam_card,student_name,Location,Grade) "
				+ "VALUES(?,?,?,?,?,?)";
		
		

		 try {
			 connection = daoTest.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, type);
			preparedStatement.setString(2, id_card);
			preparedStatement.setString(3, exam_card);
			preparedStatement.setString(4, student_name);
			preparedStatement.setString(5, location);
			preparedStatement.setInt(6, grade);
			
			preparedStatement.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				daoTest.releaseConnection(connection);
			}
			
		}

	}

	
	public List<Student> getAll(){
		
		List<Student>  students= new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		 
		DaoTest daoTest  = new DaoTest();
		
		try {
			connection = daoTest.getConnection();
			
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
			daoTest.releaseConnection(connection);
		}

		return students;

	}
	
}
