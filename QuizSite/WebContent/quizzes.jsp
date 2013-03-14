<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="java.util.*" %>
<%@ page import="db.*" %>
<%@ page import="quiz.*" %>

<head>
<title>Quizzes</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<div class="navbarPanel">


<div class="right">
<ul class="navbar">
<li class="inline navbarItem"><a href="homepage.jsp">Home</a></li>
<li class="inline navbarItem"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline navbarItem"><a href="inbox.jsp">Inbox</a></li>
<li class="inline navbarItem logoutButton">
	<form action="LogoutServlet" method="post" class="inline">
		<p class="inline"><input type="submit" value="Logout" /></p>
	</form>
</li>
</ul>
</div>

<div class="inline">
<p><img class="logo" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png"></img></p>
</div>
</div>

<h1>All Quizzes</h1>

<table class="table4">
<tr>
<th>Quiz Title</th>
<th>Created By</th>
<th>Date</th>
</tr>

<% List<Integer> quizIds = UserInfo.getQuizzesByTitle(); %>
<% if (quizIds != null) { %>
<% for(Integer id : quizIds) { %>
<%	Quiz quiz = UserInfo.getQuiz(id); %>
<tr>
<td><a href="quiz.jsp?id=<%= id %>"><%= quiz.getName() %></a></td>
<td><%= quiz.getAuthor()%></td>
<td><%= UserInfo.getDateForQuiz(id) %></td>
</tr>
<% } } %>
<tr>

</table>


</body>

</html>