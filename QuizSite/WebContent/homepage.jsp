<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="java.util.*" %>
<%@ page import="quiz.*" %>

<head>
<% String username = (String)session.getAttribute("username"); %>
<title>Home</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body>

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline"><a href="inbox.jsp">Inbox</a></li>
<li class="inline">
	<form action="LogoutServlet" method="post" class="inline">
		<input type="submit" value="Logout" />
	</form>
</li>
</ul>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<p class="announce">ANNOUNCE: Important Site Announcement</p>

<div class="panel1">

<p class="welcome">Welcome <%= username %></p>

<div class="achievements">
<% List<Integer> achievements = UserInfo.getAchievements(username); %>
<% for(Integer i : achievements) {%>
	<p> <%=AchievementInfo.getAchievement(i) %></p>
	<%} %>
</div>

<h3 class="inline">Message Activity</h3>
<ul class="list">
	<% List<Message> messages = UserInfo.getMessages(username); %>
	<% if(messages.isEmpty()) %><li>No new messages!</li><%; %>
	<% Iterator<Message> messageIter = messages.iterator(); %>
	<% for(int i = 0; i < 5; i++) {%>
		<% if(!messageIter.hasNext()) break; %>
		<% Message cur = messageIter.next(); %>
		<li><%=cur.getFrom() %> sent you a <%=cur.getTypeName() %>!</li>
	<% } %>
</ul>

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
<th>Name</th>
<th>Score</th>
</tr>

<% List<Score> history = UserInfo.getHistory(username); %>
<% for(int i = 0; i < 5; i++) { %>
<% if(history.size() > i) { %>
<%	Score score = history.get(i); %>
<tr>
<td><%= UserInfo.getQuiz(score.getQuizId()).getName() %></td>
<td><%= score.getScore()%></td>
<% } } %></tr>
</table>
</td>

<td>
<table class="table2">
<tr>
<th>Name</th>
<th>Date</th>
</tr>
<tr>
<td>Name 1</td>
<td>Date 1</td>
</tr>
</table>
</td>

</tr>
</table>
</div>

<div class="panel2">

<h3>Popular Quizzes</h3>

<ul class="list1">

<% List<Integer> popular = UserInfo.popularQuizIds(); %>
<% if(!popular.isEmpty()) { %>
<% for (int i = 0; i < 10; i++) {%>
<% if(popular.size() > i) { %>
<% int id = popular.get(i); %>
<li><%=i + 1 %>. <%=UserInfo.getQuiz(id).getName() %></li>
<% } } } %>

</ul>

<h3>Recently Created</h3>

<ul class="list1">

<% List<Integer> recent = UserInfo.recentlyCreatedQuizIds(); %>
<% if(!recent.isEmpty()) { %>
<% for (int i = 0; i < 5; i++) {%>
<% if(recent.size() > i) { %>
<% int id = recent.get(i); %>
<li><%=UserInfo.getQuiz(id).getName() %></li>
<% } } } %>

</ul>

<h3 class="inline">Friend Activity</h3>
<p class="inline"><a href="friends.jsp">(See Friends)</a></p>
<table class="table3">
<tr>
<th>Name</th>
<th>Date</th>
<th>Score</th>
</tr>
<tr>
<td>Name 1</td>
<td>Date 1</td>
<td>Score 1</td>
</tr>
</table>

</div>

</body>
</html>