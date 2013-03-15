<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="java.util.List" %>
<%String tag = (String) request.getParameter("tagType");%>
<%List<Integer> quizzes = UserInfo.getQuizzesForTag(tag);%>
<head>
<% String username = (String)session.getAttribute("username"); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quizzes with Tag: <%=tag%></title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body>

<%@include file="navbar.jsp" %>

<h1>Quizzes with Tag: <%=tag%></h1>
<ul class="list">
	<% if(quizzes.isEmpty()) %><li>No quizzes with this tag!</li><%; %>
	<% for(Integer quizId : quizzes) {%>
		<li>
		<form action="quiz.jsp" method="post">
           <input type="hidden" name="id" value="<%=quizId%>" />
           <input type="submit" class="submitLink" value="<%=UserInfo.getQuizName(quizId)%>" />
     	</form>    
		</li>
	<% } %>
</ul>
</body>

</html>