<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.UserInfo" %>

<head>
<title>Friends</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<div class="centered">
<h1>Create New Quiz</h1>
<p>Please enter the information for your new quiz:</p>
<form action="CreateServlet" method="post">
<p>Quiz Name: <input type="text" name="quizname" /></p>
<p>Description: <input type="text" name="description"/><p>
<p>Would you like your quiz to have randomly ordered questions? <input type="checkbox" name="random" value="random"/> </p>
<p>Would you like your quiz to have a separate page for each question? <input type="checkbox" name="multipage" value="multipage"/> </p>
<p>Would you like your quiz to give immediate correction? <input type="checkbox" name="immediate" value="immediate"/> </p>
<p>Would you like your quiz to allow practice mode? <input type="checkbox" name="practice" value="practice"/> </p>
<p><input type="submit" value="Add New Question"/> 
<select name="questionType">
<option value="0">Multiple Choice</option>
<option value="1">Picture Question</option>
<option value="3">Response Question</option>
<option value="2">Fill in the Blank Question</option>
</select>
</p>
</form>
</div>

</body>

</html>


