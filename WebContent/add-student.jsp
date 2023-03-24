<%@ page import="java.util.*, com.andoliver.web.jdbc.model.*" %>

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

<section id="container">
	<div id="content">
		<form method="get" action="StudentControllerServlet" id="add-form">
			<p>Add Student</p>
		
			<label for="firstName">First name</label><br>
			<input type="text" name="firstName" required="required"><br><br>

			<label for="lastName">Last name</label><br>
			<input type="text" name="lastName" required="required"><br><br>
			
			<label for="email">E-mail</label><br>
			<input type="text" name="email" required="required"><br><br>
			
			<input type="hidden" name="command" value="ADD">
			
			<input type="submit" value="Save">
			<button><a href="StudentControllerServlet">Back to list</a></button>
		</form>
	</div>
</section>


</body>

</html>