package com.atguigu.javaweb.mvc;

public class Student {

	private Integer flow_id;
	private Integer type;
	private String id_card;
	private String exam_card;
	private String student_name;
	private String location;
	private Integer grade;
	
	
	public Integer getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(Integer flow_id) {
		this.flow_id = flow_id;
	}
	public int getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getExam_card() {
		return exam_card;
	}
	public void setExam_card(String exam_card) {
		this.exam_card = exam_card;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Student(Integer flow_id, Integer type, String id_card, String exam_card, String student_name, String location,
			Integer grade) {
		super();
		this.flow_id = flow_id;
		this.type = type;
		this.id_card = id_card;
		this.exam_card = exam_card;
		this.student_name = student_name;
		this.location = location;
		this.grade = grade;
	}
	public Student() {
		
	}
	
	
}
