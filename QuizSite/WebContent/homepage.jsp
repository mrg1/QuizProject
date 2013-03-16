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
<script type="text/javascript" src="jquery-1.2.2.pack.js"></script>
<script type="text/javascript" src="htmltooltip.js"></script>
</head>

<body>

<%@include file="navbar.jsp" %>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<% List<Announcement> announcements = UserInfo.getAnnouncments(); %>
<% if(!announcements.isEmpty()) {%>
<div class="announcementPanel">
<h3 class="inline announceTitle">Site Announcements:</h3>
<% Iterator<Announcement> announceIter = announcements.iterator(); %>
<% for(int i = 0; i < 3; i++) {%>
	<% if(!announceIter.hasNext()) break; %>
	<% Announcement cur = announceIter.next(); %>
	<p class="announce"><a href="user.jsp?user=<%=cur.getUsername() %>"><%=cur.getUsername() %></a> posted "<%=cur.getContent() %>"</p>
<% } %>
</div>
<% } %>

<div class="homepageLeft">

<% if(loggedIn) { %>
<div class="welcomePanel">
<%if(UserInfo.getProfilePicture(username).isEmpty()) {%>
	<img class="inline profile" align="middle" src="http://www.almostsavvy.com/wp-content/uploads/2011/04/profile-photo.jpg"></img>
<%} else { %>
	<img class="inline profile" align="middle" src=<%=UserInfo.getProfilePicture(username)%> />
<%} %>	
<p class="welcome inline">Welcome <a href="user.jsp?user=<%=username%>"><%= username %></a></p>
</div>

<div class="achievementMessagePanel">
<h3>Achievements:</h3>
<% List<Integer> achievements = UserInfo.getAchievements(username); %>
<% for(Integer i : achievements) {%>
	<p href="#i" class="achievements inline" rel="htmltooltip"><%=AchievementInfo.getAchievement(i) %></p>

	<div class="htmltooltip" id="i">
		<p><b><%=AchievementInfo.getAchievement(i) %></b></p>
		<p><%=AchievementInfo.getAchievementDesc(i)%></p>
	</div>
<%} %>


<h3>Message Activity</h3>
<ul class="messageList">
	<% List<Message> messages = UserInfo.getMessages(username); %>
	<% if(messages.isEmpty()) %><li>No messages!</li><%; %>
	<% Iterator<Message> messageIter = messages.iterator(); %>
	<% for(int i = 0; i < 5; i++) {%>
		<% if(!messageIter.hasNext()) break; %>
		<% Message cur = messageIter.next(); %>
		<li><%=cur.getFrom() %> sent you a <%=cur.getTypeName() %>!</li>
	<% } %>
</ul>
</div>

<h3 class="inline">Your Recent History</h3>
<p class="inline"><a href="history.jsp">(See Full History)</a></p>

<table class="table1">
<tr>
<th>Tests Taken</th>
<th>Tests Created</th>
</tr>
<tr valign="top">

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
<td><a href="quiz.jsp?id=<%=score.getQuizId()%>"><%= UserInfo.getQuizName(score.getQuizId()) %></a></td>
<td><%= score.getScore()%>%</td>
</tr>
<% } } } %>
</table>
</td>

<td>
<table class="table2" width="250">
<tr valign="top">
<th>Title</th>
<th>Date</th>
</tr>
<% List<Integer> historyCreated = UserInfo.getAuthorHistory(username); %>
<% for(int i = 0; i < 5; i++) { %>
<tr>
<% if(historyCreated.size() > i) { %>
<%	int id = historyCreated.get(i); %>
<td><a href="quiz.jsp?id=<%=id%>"><%= UserInfo.getQuizName(id) %></a></td>
<td><%=UserInfo.getDateForQuiz(id)%></td>
</tr>
<% } } %>
</table>
</td>

</tr>
</table>
</div>

<% } %>
<div class="homepageRight">

<h3>Popular Quizzes</h3>

<h4>Most Taken</h4>
<ul class="list1">

<% List<Integer> taken = UserInfo.popularQuizIds(); %>
<% if(!taken.isEmpty()) { %>
<% for (int i = 0; i < 5; i++) {%>
<% if(taken.size() > i) { %>
<% int id = taken.get(i); %>
<li><%=i + 1 %>. <a href="quiz.jsp?id=<%=id%>"><%=UserInfo.getQuizName(id) %></a></li>
<% } } } %>

<h4>Highest Rated</h4>
<ul class="list1">

<% Map<Integer, Double> rated = UserInfo.getHighestRatedQuizzes(); %>
<% if(!rated.isEmpty()) { %>
<% for (int i = 0; i < 5; i++) {%>
<% if(rated.size() > i) { %>
<li><%=i + 1 %>. <a href="quiz.jsp?id=<%=id%>"><%=UserInfo.getQuizName(id) %></a></li>
<% } } } %>

</ul>

<h3>Recently Created</h3>

<ul class="list1">

<% List<Integer> recent = UserInfo.recentlyCreatedQuizIds(); %>
<% if(!recent.isEmpty()) { %>
<% for (int i = 0; i < 5; i++) {%>
<% if(recent.size() > i) { %>
<% int id = recent.get(i); %>
<li><a href="quiz.jsp?id=<%=id%>"><%=UserInfo.getQuizName(id) %></a></li>
<% } } } %>

</ul>

<% if(loggedIn) { %>
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
<td><a href="quiz.jsp?id=<%=s.getQuizId()%>"><%= UserInfo.getQuizName(s.getQuizId()) %></a></td>
<td><%= s.getScore()%>%</td>
<% } } %>
</tr>
</table>

</div>
<% } %>
</body>
</html>
