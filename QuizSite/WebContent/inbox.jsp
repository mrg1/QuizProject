<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="java.util.List" %>
<head>
<% String username = (String)session.getAttribute("username"); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
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

<h1>Inbox</h1>
<a href="sendMessage.jsp" class="inline">(Create Message)</a>
<ul class="list">
	<% List<Message> messages = UserInfo.getMessages(username); %>
	<% if(messages.isEmpty()) %><li>Nothing yet!</li><%; %>
	<% for(Message cur : messages) {%>
		<li class="message"><%= cur.getHtml() %>
		<form action="DeleteMessageServlet" method="post">
           <input type="hidden" name="id" value="<%=cur.getMessageId() %>" />
           <input type="submit" value="Remove" />
     	</form>    
		</li>
	<% } %>
</ul>
</body>

</html>