<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
	body
	{
		background-color:#b0c4de;
	}
</style>


	<form action="addStudent"  method="post">
		<table>
			Type: <input type="text" name="type"/><br>
			IdCard: <input type="text" name="idCard"/><br>
			ExamCard: <input type="text" name="examCard"/><br>
			StudentName: <input type="text" name="StudentName"/><br>
			Location: <input type="text" name="location"/><br>
			Grade: <input type="text" name="grade"/><br>
			
			<input type="submit" value="Submit" />
		</table>
	</form>	

</body>
</html>