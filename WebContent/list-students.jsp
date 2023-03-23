<%@ page import="java.util.*, com.andoliver.web.jdbc.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
	<title>Student Tracker APP</title>
</head>

<%
	List<Student> students = (List<Student>)request.getAttribute("STUDENTS_LIST");
%>

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
		</tr>
		<c:forEach var="student" items="<%= students %>">
			<tr>
				<td>${student.getFirstName()}</td>
				<td>${student.getLastName()}</td>
				<td>${student.getEmail()}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</section>


</body>

</html>