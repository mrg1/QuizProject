<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a New Quiz</title>
</head>
<body>
<%@include file="navbar.html" %>
<img class="centeredImage" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png">

<div class="centered">
<h1>Create New Account</h1>
<p>Please enter proposed name and password.</p>
<form action="CreateServlet" method="post">
<p>User Name: <input type="text" name="username" /></p>
<p>Password: <input type="password" name="password"/><p>
<input class="loginButton" type="submit" value="Create Account"/>
</form>

<p class="centered"><a href="index.html">Return To Login</a></p>

</div>
</body>
</html>