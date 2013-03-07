<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="message.*" %>

<head>
<% String username = (String)session.getAttribute("username"); %>
<title>Home</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css" />
</head>

<body>

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="news.asp">Quizzes</a></li>
<li class="inline"><a href="about.asp">Messages</a></li>
</ul>

<p class="announce">ANNOUNCE: Important Site Announcement</p>

<div class="panel1">

<p class="welcome">Welcome <%= username %></p>

<p class="achievements">Achievements go here</p>

<h3>Inbox</h3>
<a href="sendMessage.jsp">Create Message</a>
<ul class="list">
	<% for(Message cur : UserInfo.getMessages(username)) {%>
		<li class="message"><%= cur.getHtml() %></li>
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
<th>Date</th>
<th>Score</th>
</tr>
<tr>
<td>Name 1</td>
<td>Date 1</td>
<td>Score 1</td>
</tr>
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
<li>1. Quiz 1</li>
<li>2. Quiz 2</li>
<li>3. Quiz 3</li>
<li>4. Quiz 4</li>
<li>5. Quiz 5</li>
</ul>

<h3>Recently Created</h3>
<ul class="list1">
<li>Quiz 1</li>
<li>Quiz 2</li>
<li>Quiz 3</li>
<li>Quiz 4</li>
<li>Quiz 5</li>
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