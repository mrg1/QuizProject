<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="db.*" %>
<%@ page import="user.*" %>
<%@ page import="java.util.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
<% String username = (String)session.getAttribute("username"); %>
<% User user = UserInfo.getUser(username); %>
<%// if(!UserInfo.isAdmin(username)) out.println("<meta http-equiv=\"REFRESH\" content=\"0;url=homepage.jsp\">"); %>
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
	<p>Create Announcement:<br /><textarea rows="4" cols="50" name="content"></textarea></p>
	<p><input type="submit" value="Announce" /></p>
</form>


<h2>Administrators</h2>

<% List<User> users = UserInfo.getUsers();
List<User> admins = new ArrayList<User>();
for(User u : users) {
	if(UserInfo.isAdmin(u.getUsername())) {
		admins.add(u);
	}
} %>
<table class="adminTable">
<%for(User cur : admins) { %>
	<tr><th></th><th></th></tr>
	<tr>
	
	<td>
	<form action="RemoveAdminServlet" method="post">
		<input type="hidden" name="user" value=<%=cur.getUsername() %> />
		<input type="submit" value="Remove Admin" />
	</form>
	</td>
	<td><%=cur.getUsername() %></td>
	</tr>
<% } %>
</table>

<h2>Users</h2>
<table class="adminTable">
	<tr><th></th><th></th><th></th></tr>
<% if(users.isEmpty()) %><tr><td>No users yet!</td></tr>
<% for(User cur : users) { %>
	<tr>
	<td>
	<form action="PromoteServlet" method="post">
		<input type="hidden" name="user" value=<%=cur.getUsername() %> />
		<input type="submit" value="Promote" />
	</form>
	</td>
	<td>
	<form action="DeleteUserServlet" method="post">
		<input type="hidden" name="user" value=<%=cur.getUsername() %> />
		<input type="submit" value="Delete" />
	</form>
	</td>
	<td><a href="user.jsp?user=<%=cur.getUsername() %>"><%=cur.getUsername() %></a></td>
	</tr>
<% } %>
</table>

<h3>Reported as Inappropriate</h3>
<table class="adminTable">
	<tr><th></th><th></th><th></th></tr>
<%Map<Integer,Integer> reported = UserInfo.getReports(); %>
<%if(reported.isEmpty()) %><tr><td>No quizzes reported.</td></tr>
<%for(int id : reported.keySet()) {%>
	<tr>
	<td>
	<form action="DeleteQuizServlet" method="post">
		<input type="hidden" name="quizId" value=<%=id %> />
		<input type="submit" value="Delete" />
	</form>
	</td>
	<td>
	<form action="UnreportServlet" method="post">
		<input type="hidden" name="quizId" value=<%=id %> />
		<input type="submit" value="Mark Appropriate" />	
	</form>
	</td>
	<td><a href="quiz.jsp?id=<%= id %>"><%=UserInfo.getQuiz(id).getName() %></a>, id: <%=id %>, # of reports: <%=reported.get(id)%></td>
	</tr>
<%} %>
</table>
<h3>All Quizzes</h3>
<table class="adminTable">
	<tr><th></th><th></th><th></th></tr>
<% List<Integer> quizIDs = UserInfo.getQuizzesByTitle(); %>
<% if(quizIDs.isEmpty()) %><tr><td>No quizzes yet!</td></tr>
<% for(Integer id : quizIDs) { %>
	<tr>
	<td>
	<form action="ClearQuizHistoryServlet" method="post">
		<input type="hidden" name="quizID" value=<%=id %> />
		<input type="submit" value="Clear History" />
	</form>
	</td>
	<td>
	<form action="DeleteQuizServlet" method="post">
		<input type="hidden" name="quizID" value=<%=id %> />
		<input type="submit" value="Delete" />
	</form>
	</td>
	<td><a href="quiz.jsp?id=<%= id %>"><%=UserInfo.getQuiz(id).getName() %></a>, id: <%=id %></td>
<% } %>
</table>

</body>
</html>
