<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.UserInfo" %>

<head>
<title>Friends</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline"><a href="about.asp">Messages</a></li>
</ul>

<h1>Friends for <%= request.getParameter("username") %>:</h1>
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
<tr>
<td>Friend 1</td>
<td><a href="friends.jsp">Remove</a></td>
</tr>
<tr>
<td>Friend 2</td>
<td><a href="friends.jsp">Remove</a></td>
</tr>
<tr>
<td>Friend 2</td>
<td><a href="friends.jsp">Remove</a></td>
</tr>
</table>

</body>

</html>