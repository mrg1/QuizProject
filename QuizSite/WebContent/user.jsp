<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="java.util.*" %>
<%@ page import="quiz.*" %>


<head>
<% String user = request.getParameter("user"); %>
<% String username = (String)session.getAttribute("username"); %>
<title><%= user %></title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>

<div>
<%if(UserInfo.getProfilePicture(user).isEmpty()) {%>
	<img class="inline profile" src="http://www.almostsavvy.com/wp-content/uploads/2011/04/profile-photo.jpg"></img>
<%} else { %>
	<img class="inline profile" src=<%=UserInfo.getProfilePicture(user) %> />
<%} %>	
<h1 class="inline"><%= user %></h1>
</div>
<% if(loggedIn && !UserInfo.getFriends(username).contains(user)&&!user.equals(username)) {%>
<form action="SendRequestServlet" method="post">
<div>
<input type="hidden" name="to" value=<%=user %> />
<input type="hidden" name="content" value="I would like to be your friend!" />
<input type="submit" value="Add Friend" />
</div>
</form>
<% } %>
<%if(loggedIn && user.equals(username)) {%>
	<form action="ProfilePictureServlet" method="post">
	<p>Image: <input type="text" name="image" />
	<input type="submit" value="Change Profile Picture" /></p>
	</form>
<%} %>
<h3>Achievements:</h3>
<% List<Integer> achievements = UserInfo.getAchievements(username); %>
<% for(Integer i : achievements) {%>
	<p href="#i" class="achievements" rel="htmltooltip"><%=AchievementInfo.getAchievement(i) %></p>

	<div class="htmltooltip" id="i">
		<p><b><%=AchievementInfo.getAchievement(i) %></b></p>
		<p><%=AchievementInfo.getAchievementDesc(i)%></p>
	</div>
<%} %>

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
<td><%=UserInfo.getDateForQuiz(id)%></td>
</tr>
<% } } %>
</table>
</td>

</tr>
</table>

</body>

</html>
