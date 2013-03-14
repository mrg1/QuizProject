<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
</head>
<body>
<h1>Site Statistics</h1>
<p>Number of users: </p>
<p>Number of quizzes: </p>

<h1>Actions</h1>
<form action="AnnounceServlet" method="post">
	<p>Create Announcement: <textarea rows="4" cols="50" name="content"></textarea></p>
	<p><input type="submit" value="Announce" /></p>
</form>

<form action="PromoteServlet" method="post">
	<p>Promote User: <input type="text" name="user" /></p>
	<p><input type="submit" value="Promote" /></p>
</form>

<form action="DeleteUserServlet" method="post">
	<p>Delete User: <input type="text" name="user" /></p>
	<p><input type="submit" value="Delete" /></p>
</form>

<form action="ClearQuizHistoryServlet" method="post">
	<p>Clear Quiz History: <input type="text" name="quiz" /></p>
	<p><input type="submit" value="Clear" /></p>
</form>

<form action="DeleteQuizServlet" method="post">
	<p>Delete Quiz: <input type="text" name="quiz" /></p>
	<p><input type="submit" value="Delete" /></p>
</form>

</body>
</html>