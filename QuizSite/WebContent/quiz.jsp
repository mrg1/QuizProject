<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="quiz.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.NumberFormat" %>

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

<div class="leftQuiz">
<h1><%= quiz.getName() %></h1>


<form action="ReportServlet" method="post">
	<p class="achievements">Created by <a href="user.jsp?user=<%=quiz.getAuthor()%>"><%= quiz.getAuthor() %></a>. <%=quiz.getDescription() %>
	<input type="hidden" name="quizId" value=<%=request.getParameter("id") %> />
	<input type="submit" value="Report Inappropriate" /></p>
</form>
<p class="achievements inline">Tags: </p>
<% List<String> tags = UserInfo.getTagsForQuiz(quiz.getQuizId()); %>
<% for (String s : tags) { %>
<form class="inline" action="display-quizzes-for-tag.jsp">
	<input type="hidden" name="tagType" value="<%=s %>"/>
	<input class="sumbitLink" type="submit" value="<%=s %>"/>
</form>
<% } %>
</div>


<div class="rightQuiz">
<% if(quiz.isOnePage()) { %>
<% if(loggedIn) { %>
<% out.println("<button class=\"takeButton\" onclick=\"window.location = 'quiz-content.jsp?id="+quiz.getQuizId()+"'\"><h1>Take Quiz</h1></button>"); %>	
<% } %>
<% } else { %>
<% out.println("<button class=\"takeButton\" onclick=\"window.location = 'quiz-content-multipage.jsp?id="+quiz.getQuizId()+"&q=0'\"><h1>Take Quiz</h1></button>"); %>
<% } %>
</div>


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
<% sumScore /= n; %>
<% sumTime /= n; %>
<% } %>

<table class="statsTable">

<tr>
<th>Average Rating</th>
<th>Average Score</th>
<th>Average Time</th>
<th>Number of Attempts</th>
</tr>

<tr>
<%NumberFormat format = NumberFormat.getInstance(); %>
<%format.setMaximumFractionDigits(1); %>
<%if(UserInfo.getAverageRating(Integer.parseInt(request.getParameter("id")))==-1) {%>
	<td align="center"><h1>No ratings yet!</h1></td>
<%} else { %>
	<td align="center"><h1><%=format.format(UserInfo.getAverageRating(Integer.parseInt(request.getParameter("id")))) %></h1></td>
<% } %>
<td align="center"><h1><%=sumScore %></h1></td>
<td align="center"><h1><%=sumTime %> seconds</h1></td>
<td align="center"><h1><%=n %> attempts</h1></td>
</tr>

</table>

<div class="panel3">

<h3>Your Past Attempts</h3>
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

<h3>Recent Reviews</h3>
<table class="table5 sortable">
<tr>
<th>User</th>
<th>Rating</th>
<th>Comments</th>
</tr>


<% List<Rating> reviews = UserInfo.getRatingsForQuiz(quiz.getQuizId()); %>
<% if (reviews != null) { %>
<% for(int i = 0; i < 5; i++) { %>
<% if(reviews.size() > i) { %>
<%	Rating r = reviews.get(i); %>
<tr>
<td><%= r.getUsername() %></td>
<td><%= r.getRating() %></td>
<td><%= r.getReview() %></td>
</tr>
<% } } } %>


</table>
</div>

<div class="panel4">

<h3>Recent Attempts by All Users</h3>
<table class="table2">
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


</body>

</html>
