<%@ page import="java.util.*, com.andoliver.web.jdbc.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
	<title>Student Tracker APP</title>
	<link type="text/css" rel="stylesheet" href="./css/style.css">
</head>

<c:set var="students" value='<%= (List<Student>)request.getAttribute("STUDENTS_LIST") %>' />

<body>

<header id="wrapper">
	<div id="header">
		<h2>FooBar University</h2>
	</div>
</header>

<section id="container">
	<div id="content">
		<table border="1">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<c:url var="tempLink" value="StudentControllerServlet">
				<c:param name="command" value="LOAD"/>
				<c:param name="studentId" value="${student.getId()}"/>
			</c:url>
		
			<tr>
				<td>${student.getFirstName()}</td>
				<td>${student.getLastName()}</td>
				<td>${student.getEmail()}</td>
				<td><a href="${tempLink}">Update</a></td>
			</tr>
		</c:forEach>
		</table>
		<button class="add-student-button"><a href="add-student.jsp">Add Student</a></button>
	</div>
	
</section>


</body>

</html>