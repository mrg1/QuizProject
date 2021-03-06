<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.UserInfo" %>

<head>
<title>Create Quiz</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<div class="centered" style="width:500px">
<h1>Create New Quiz</h1>
<p>Please enter the information for your new quiz:</p>
<form action="QuizCreationServlet" method="post">
<table>
<tr><td>Quiz Name:</td><td><input type="text" name="quizname" /></td></tr>
<tr><td valign="top">Description:</td><td><textarea rows="4" cols="50" name="description"></textarea></td></tr>
<tr><td colspan="2">Enter tags to describe your quiz, like "Sports" or "General Knowledge."<br />Please separate each tag with a new line.</td></tr>
<tr><td valign="top">Tags:</td><td><textarea rows="4" cols="50" name="tags"></textarea></td></tr>
</table>
<br />


<p><input type="checkbox" name="random" value="random"/>Would you like your quiz to have randomly ordered questions?</p>

<p><input type="checkbox" name="multipage" value="multipage"/>Would you like your quiz to have a separate page for each question?</p>

<p><input type="checkbox" name="immediate" value="immediate"/>Would you like your quiz to give immediate correction?</p>

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


