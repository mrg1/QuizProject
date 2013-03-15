<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="quiz.*" %>
<%@ page import="java.util.*" %>

<head>
<% Quiz quiz = UserInfo.getQuiz(Integer.parseInt(request.getParameter("id"))); %>
<% String username = (String)session.getAttribute("username"); %>
<title><%= quiz.getName() %></title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
<script src="sorttable.js"></script>
</head>

<body>

<%@include file="navbar.jsp" %>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<h1><%= quiz.getName() %></h1>

<p class="achievements">Created by <a href="user.jsp?user=<%=quiz.getAuthor()%>"><%= quiz.getAuthor() %></a>. <%=quiz.getDescription() %></p>

<div class="panel3">

<% List<Score> allScores = UserInfo.getRecentQuizAttempts(quiz.getQuizId()); %>
<% int sumScore = 0; %>
<% int sumTime = 0; %>
<% int n = 0; %>
<% for (Score s : allScores) {%>
<% 		sumScore += s.getScore(); %>
<% 		sumTime += s.getElapsed(); %>
<%		n++; %>
<% } %>
<% if (n != 0) {%>
<% int avgScore = sumScore/n; %>
<% int avgTime = sumTime/n; %>
<% } %>

<h3>Average score: <%=avgScore %>%</h3>
<h3>Average time: <%=avgTime %> seconds</h3>
<h3>Number of attempts: <%=n %> attempts</h3>

<table class="table5 sortable">
<tr>
<th>Date</th>
<th>Elapsed</th>
<th>Past Scores</th>
</tr>


<% List<Score> quizHistory = UserInfo.getUserHistoryOnQuiz(username, quiz.getQuizId()); %>
<% if (quizHistory != null) { %>
<% for(int i = 0; i < 5; i++) { %>
<% if(quizHistory.size() > i) { %>
<%	Score score = quizHistory.get(i); %>
<tr>
<td><%= UserInfo.getDateForScore(score.getScoreId()) %></td>
<td><%= score.getElapsed() %></td>
<td><%= score.getScore()%>%</td>
</tr>
<% } } } %>


</table>
</div>

<div class="panel4">

<h3>Recent Attempts</h3>
<table class="table2 sortable">
<tr>
<th>User</th>
<th>Score</th>
</tr>

<% List<Score> recent = UserInfo.getRecentQuizAttempts(quiz.getQuizId()); %>
<% if (recent != null) { %>
<% for(int i = 0; i < 5; i++) { %>
<% if(recent.size() > i) { %>
<%	Score score = recent.get(i); %>
<tr>
<td><a href="user.jsp?user=<%=score.getUsername()%>"><%= score.getUsername() %></a></td>
<td><%= score.getScore()%>%</td>
</tr>
<% } } } %>
</table>

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

<% List<Score> dayHistory = UserInfo.getDaysQuizAttempts(quiz.getQuizId()); %>
<% if (dayHistory != null) { %>
<% for(int i = 0; i < 10; i++) { %>
<% if(dayHistory.size() > i) { %>
<%	Score score = dayHistory.get(i); %>
<tr>
<td><a href="user.jsp?user=<%=score.getUsername()%>"><%= score.getUsername() %></a></td>
<td></td>
<td><%= score.getScore()%>%</td>
</tr>
<% } } } %>
</table>
</td>

<td>
<table class="table2">
<tr>
<th>Name</th>
<th>Date</th>
<th>Score</th>
</tr>

<% List<Score> totalHistory = UserInfo.getTopTen(quiz.getQuizId()); %>
<% if (totalHistory != null) { %>
<% for(int i = 0; i < 10; i++) { %>
<% if(totalHistory.size() > i) { %>
<%	Score score = totalHistory.get(i); %>
<tr>
<td><a href="user.jsp?user=<%=score.getUsername()%>"><%= score.getUsername() %></a></td>
<td></td>
<td><%= score.getScore()%>%</td>
</tr>
<% } } } %>

</table>
</td>

</tr>
</table>

</div>


<button onclick="window.location = 'quiz-summary.jsp'">Practice</button>
<% if(quiz.isOnePage()) { %>
<% out.println("<button onclick=\"window.location = 'quiz-content.jsp?id="+quiz.getQuizId()+"'\">Take Quiz</button>"); %>	
<% } else { %>
<% out.println("<button onclick=\"window.location = 'quiz-content-multipage.jsp?id="+quiz.getQuizId()+"&q=0'\">Take Quiz</button>"); %>
<% } %>
<button onclick="window.location = 'quiz-summary.jsp'">Edit</button>



</body>

</html>
