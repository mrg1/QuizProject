<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="quiz.*" %>
<%@ page import="user.*" %>

<head>
<% Quiz quiz = UserInfo.getQuiz(Integer.parseInt(request.getParameter("id"))); %>
<title>Quiz 1</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline"><a href="about.asp">Messages</a></li>
</ul>

<h1><%= quiz.getName() %></h1>

<p class="achievements">Created By Username. Summary goes of quiz goes here too.</p>

<div class="panel3">
<table class="table5">
<tr>
<th>Date</th>
<th>Score</th>
</tr>
<tr>
<td>Date 1</td>
<td>Score 1</td>
</tr>
</table>
</div>

<div class="panel4">

<h3>Recent Attempts</h3>
<ul class="list1">
<li>1. Attempt 1</li>
<li>2. Attempt 2</li>
<li>3. Attempt 3</li>
<li>4. Attempt 4</li>
<li>5. Attempt 5</li>
</ul>

<h3>Top Scores</h3>
<table class="table1">
<tr>
<th>Last Day</th>
<th>All Time</th>
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
<th>Score</th>
</tr>
<tr>
<td>Name 1</td>
<td>Date 1</td>
<td>Score 1</td>
</tr>
</table>
</td>

</tr>
</table>

</div>

<p class="quiz-buttons">
<button onclick="window.location = 'quiz-summary.jsp'">Practice</button>
<button onclick="window.location = 'quiz-summary.jsp'">Take</button>
<button onclick="window.location = 'quiz-summary.jsp'">Edit</button>
</p>

</body>

</html>