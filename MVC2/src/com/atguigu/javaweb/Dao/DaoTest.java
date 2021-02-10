package com.atguigu.javaweb.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoTest {
	
	
	
	
	public Connection getConnection(){
		
		Connection connection = null;
		
		try {
			
			String driverClass= "com.mysql.jdbc.Driver";
			
			String url = "jdbc:mysql://192.168.3.26:3306/ajax";
			String user = "root";
			String password = "1230";
			
			Class.forName(driverClass);
			
			connection = DriverManager.getConnection(url, user, password);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return connection;
		}
	
	public void releaseConnection(Connection connection){
			
			try {
				if(connection != null){
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public DaoTest() {
		
	}
}
