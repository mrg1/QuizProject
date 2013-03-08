<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="java.util.List" %>
<%@ page import="quiz.*" %>

<head>
<% String username = (String)session.getAttribute("username"); %>
<title>History</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body>

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline"><a href="messages.jsp">Messages</a></li>
</ul>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>


<p class="welcome">History for <%= username %></p>

<table class="table5">
<tr>
<th>Title</th>
<th>Score</th>
</tr>

<% for(Score score : UserInfo.getHistory(username)) { %>
<tr>
<td><%= UserInfo.getQuiz(score.getQuizId()).getName() %></td>
<td><%= score.getScore()%></td>
<% } %></tr>
</table>


</body>
</html>