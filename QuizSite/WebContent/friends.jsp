<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.UserInfo" %>

<head>
<title>Friends</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<div class="navbarPanel">


<div class="right">
<ul class="navbar">
<li class="inline navbarItem"><a href="homepage.jsp">Home</a></li>
<li class="inline navbarItem"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline navbarItem"><a href="inbox.jsp">Inbox</a></li>
<li class="inline navbarItem logoutButton">
	<form action="LogoutServlet" method="post" class="inline">
		<p class="inline"><input type="submit" value="Logout" /></p>
	</form>
</li>
</ul>
</div>

<div class="inline">
<p><img class="logo" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png"></img></p>
</div>
</div>

<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<form action="SendRequestServlet" method="post">
	<p>Add Friend: <input type="text" name="to" /></p>
	<p>Message: <textarea rows="4" cols="50" name="content"></textarea></p>
	<p><input type="submit" value="Add" /></p>
</form>

<h1>Friends for <%= session.getAttribute("username") %>:</h1>
<ul>
	<%for(String cur : UserInfo.getFriends(request.getParameter("username"))) { %>
		<li><%=cur %></li>
	<%} %>
</ul>

<table class="table4">
<tr>
<th>Friend</th>
<th></th>
</tr>

<% for(String friend : UserInfo.getFriends((String) session.getAttribute("username"))) { %>

<tr>
<td><a href="user.jsp?user=<%=friend%>"><%= friend %></a></td>
<td><a href="friends.jsp">Remove</a></td>
</tr>

<%} %>

</table>

</body>

</html>