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

<%@include file="navbar.jsp" %>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<div class="panel1">

<h3 class="inline">Announcements</h3>
<% List<Announcement> announcements = UserInfo.getAnnouncments(); %>
<% if(announcements.isEmpty()) %><p class="announce">No announcements!</p><%; %>
<% Iterator<Announcement> announceIter = announcements.iterator(); %>
<% for(int i = 0; i < 3; i++) {%>
	<% if(!announceIter.hasNext()) break; %>
	<% Announcement cur = announceIter.next(); %>
	<p class="announce"><a href="user.jsp?user=<%=cur.getUsername() %>"><%=cur.getUsername() %></a> posted: <%=cur.getContent() %></p>
<% } %>


<p class="welcome">Welcome <a href="user.jsp?user=<%=username%>"><%= username %></a></p>


<% List<Integer> achievements = UserInfo.getAchievements(username);
	if(!achievements.isEmpty()) { %>
	<h3>Achievements:</h3>
	<div>
	<ul class="list">
<%		for(Integer i : achievements) {
			out.println("<li>" + AchievementInfo.getAchievement(i) + "</li>");
		} 
	}
%>
</ul>
</div>

<h3 class="inline">Message Activity</h3>
<ul class="list">
	<% List<Message> messages = UserInfo.getMessages(username); %>
	<% if(messages.isEmpty()) %><li>No messages!</li><%; %>
	<% Iterator<Message> messageIter = messages.iterator(); %>
	<% for(int i = 0; i < 5; i++) {%>
		<% if(!messageIter.hasNext()) break; %>
		<% Message cur = messageIter.next(); %>
		<li><%=cur.getFrom() %> sent you a <%=cur.getTypeName() %>!</li>
	<% } %>
</ul>

<h3 class="inline">Your Recent Activity</h3>
<p class="inline"><a href="history.jsp">(See Full History)</a></p>

<table class="table1">
<tr>
<th>Tests Taken</th>
<th>Tests Created</th>
</tr>
<tr>

<td>
<table class="table2" width="250">
<tr>
<th>Title</th>
<th>Score</th>
</tr>

<% List<Score> history = UserInfo.getHistory(username); %>
<% if (history != null) { %>
<% for(int i = 0; i < 5; i++) { %>
<% if(history.size() > i) { %>
<%	Score score = history.get(i); %>
<tr>
<td><a href="quiz.jsp?id=<%=score.getQuizId()%>"><%= UserInfo.getQuiz(score.getQuizId()).getName() %></a></td>
<td><%= score.getScore()%>%</td>
</tr>
<% } } } %>
</table>
</td>

<td>
<table class="table2" width="250">
<tr>
<th>Title</th>
<th>Date</th>
</tr>
<% List<Integer> historyCreated = UserInfo.getAuthorHistory(username); %>
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
</div>

<div class="panel2">

<h3>Popular Quizzes</h3>

<ul class="list1">

<% List<Integer> popular = UserInfo.popularQuizIds(); %>
<% if(!popular.isEmpty()) { %>
<% for (int i = 0; i < 10; i++) {%>
<% if(popular.size() > i) { %>
<% int id = popular.get(i); %>
<li><%=i + 1 %>. <a href="quiz.jsp?id=<%=id%>"><%=UserInfo.getQuiz(id).getName() %></a></li>
<% } } } %>

</ul>

<h3>Recently Created</h3>

<ul class="list1">

<% List<Integer> recent = UserInfo.recentlyCreatedQuizIds(); %>
<% if(!recent.isEmpty()) { %>
<% for (int i = 0; i < 5; i++) {%>
<% if(recent.size() > i) { %>
<% int id = recent.get(i); %>
<li><a href="quiz.jsp?id=<%=id%>"><%=UserInfo.getQuiz(id).getName() %></a></li>
<% } } } %>

</ul>

<h3 class="inline">Friend Activity</h3>
<table class="table3">
<tr>
<th>Friend</th>
<th>Quiz Title</th>
<th>Score</th>
</tr>

<% List<Score> friendHistory = UserInfo.getFriendHistory(username); %>
<% for(int i = 0; i < 5; i++) { %>
<% if(friendHistory.size() > i) { %>
<%	Score s = friendHistory.get(i); %>
<tr>
<td><a href="user.jsp?user=<%=s.getUsername()%>"><%= s.getUsername() %></a></td>
<td><a href="quiz.jsp?id=<%=s.getQuizId()%>"><%= UserInfo.getQuiz(s.getQuizId()).getName() %></a></td>
<td><%= s.getScore()%>%</td>
<% } } %>
</tr>
</table>

</div>

</body>
</html>
