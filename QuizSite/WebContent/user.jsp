<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="java.util.*" %>
<%@ page import="quiz.*" %>


<head>
<% String user = request.getParameter("user"); %>
<title><%= user %></title>
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

<h1><%= user %></h1>

<div class="achievements">
<% List<Integer> achievements = UserInfo.getAchievements(user); %>
<% for(Integer i : achievements) {%>
	<p> <%=AchievementInfo.getAchievement(i) %></p>
	<%} %>
</div>

<h3 class="inline">User's Recent Activity</h3>
<p class="inline"><a href="history.jsp">(See Full History)</a></p>

<table class="table1">
<tr>
<th>Tests Taken</th>
<th>Tests Created</th>
</tr>
<tr>

<td>
<table class="table2">
<tr>
<th>Title</th>
<th>Score</th>
</tr>

<% List<Score> history = UserInfo.getHistory(user); %>
<% if (history != null) { %>
<% for(int i = 0; i < 5; i++) { %>
<% if(history.size() > i) { %>
<%	Score score = history.get(i); %>
<tr>
<td><a href="quiz.jsp?id=<%=score.getQuizId()%>"><%= UserInfo.getQuiz(score.getQuizId()).getName() %></a></td>
<td><%= score.getScore()%></td>
<% } } } %>
</tr>
</table>
</td>

<td>
<table class="table2">
<tr>
<th>Title</th>
<th>Date</th>
</tr>
<% List<Integer> historyCreated = UserInfo.getAuthorHistory(user); %>
<% for(int i = 0; i < 5; i++) { %>
<tr>
<% if(historyCreated.size() > i) { %>
<%	int id = historyCreated.get(i); %>
<td><a href="quiz.jsp?id=<%=id%>"><%= UserInfo.getQuiz(id).getName() %></a></td>
<td>Date Here?</td>
</tr>
<% } } %>
</table>
</td>

</tr>
</table>

</body>

</html>
