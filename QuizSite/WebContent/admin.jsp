<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="db.*" %>
<%@ page import="user.*" %>
<%@ page import="java.util.List" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>
<body>

<%@include file="navbar.jsp" %>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<h1>Site Statistics</h1>
<p>Number of users: <%=UserInfo.getUsers().size() %></p>
<p>Number of quizzes: <%=UserInfo.getQuizzesByTitle().size() %></p>

<h1>Actions</h1>
<h2>Announcements</h2>
<form action="AnnounceServlet" method="post">
	<p>Create Announcement: <textarea rows="4" cols="50" name="content"></textarea></p>
	<p><input type="submit" value="Announce" /></p>
</form>

<h2>Users</h2>
<ul class="list">
<% List<User> users = UserInfo.getUsers(); %>
<% if(users.isEmpty()) %><li>No users yet!</li>
<% for(User cur : users) { %>
	<li>
	<%=cur.getUsername() %>
	<form action="PromoteServlet" method="post">
		<p><input type="hidden" name="user" value=<%=cur.getUsername() %> /></p>
		<p><input type="submit" value="Promote" /></p>
	</form>
	<form action="DeleteUserServlet" method="post">
		<p><input type="hidden" name="user" value=<%=cur.getUsername() %> /></p>
		<p><input type="submit" value="Delete" /></p>
	</form>
	</li>
<% } %>
</ul>

<h2>Quizzes</h2>
<ul class="list">
<% List<Integer> quizIDs = UserInfo.getQuizzesByTitle(); %>
<% if(quizIDs.isEmpty()) %><li>No quizzes yet!</li>
<% for(Integer id : quizIDs) { %>
	<li>
	<%=UserInfo.getQuiz(id).getName() %>, id: <%=id %>
	<form action="ClearQuizHistoryServlet" method="post">
		<p><input type="hidden" name="quizID" value=<%=id %> /></p>
		<p><input type="submit" value="Clear" /></p>
	</form>
	<form action="DeleteQuizServlet" method="post">
		<p><input type="hidden" name="quizID" value=<%=id %> /></p>
		<p><input type="submit" value="Delete" /></p>
	</form>
	</li>
<% } %>
</ul>

</body>
</html>