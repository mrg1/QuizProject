<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="java.util.*" %>
<%@ page import="db.*" %>
<%@ page import="quiz.*" %>

<head>
<title>Quizzes</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<h1>All Quizzes</h1>

<table class="table4">
<tr>
<th>Quiz Title</th>
<th>Created By</th>
<th>Date</th>
</tr>

<% List<Integer> quizIds = UserInfo.getQuizzesByTitle(); %>
<% if (quizIds != null) { %>
<% for(Integer id : quizIds) { %>
<%	Quiz quiz = UserInfo.getQuiz(id); %>
<tr>
<td><a href="quiz.jsp?id=<%= id %>"><%= quiz.getName() %></a></td>
<td><%= quiz.getAuthor()%></td>
<td><%= UserInfo.getDateForQuiz(id) %></td>
</tr>
<% } } %>
<tr>

</table>


</body>

</html>