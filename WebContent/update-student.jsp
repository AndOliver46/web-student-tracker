<%@ page import="java.util.*, com.andoliver.web.jdbc.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Student Tracker APP</title>
	<link type="text/css" rel="stylesheet" href="./css/style.css">
</head>

<body>

<header id="wrapper">
	<div id="header">
		<h2>FooBar University</h2>
	</div>
</header>

<c:set var="student" value="${studentLoaded}" />

<section id="container">
	<div id="content">
		<form method="get" action="StudentControllerServlet" id="add-form">
			<p>Update Student</p>
		
			<label for="firstName">First name</label><br>
			<input type="text" name="firstName" required="required" value="${student.firstName}"><br><br>

			<label for="lastName">Last name</label><br>
			<input type="text" name="lastName" required="required" value="${student.lastName}"><br><br>
			
			<label for="email">E-mail</label><br>
			<input type="text" name="email" required="required" value="${student.email}"><br><br>
			
			<input type="hidden" name="command" value="ADD">
			
			<input type="submit" value="Save">
			<button><a href="StudentControllerServlet">Back to list</a></button>
		</form>
	</div>
</section>


</body>

</html>