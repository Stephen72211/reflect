<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br><br><br>
	<table border="1" cellspadding="10" cellspacing="0">
		<tr align="center">
			<th>FlowId</th>	
			<th>Type</th>
			<th>IdCard</th>
			<th>ExamCard</th>
			<th>StudentName</th>
			<th>Location</th>
			<th>Grade</th>
			<th>DELETE</th>
		</tr>	
		
			<c:forEach items="${requestScope.students}" var="stu">
				<tr align="center">
					<td>${stu.getFlow_id()}</td>
					<td>${stu.getType()}</td>
					<td>${stu.getId_card()}</td>
					<td>${stu.getExam_card()}</td>
					<td>${stu.getStudent_name()}</td>
					<td>${stu.getLocation()}</td>
					<td>${stu.getGrade()}</td>
					<td><a href="deleteStudent?flowId=${stu.getFlow_id()}">Delete</a></td>
				</tr>
			</c:forEach>
	</table>



</body>
</html>