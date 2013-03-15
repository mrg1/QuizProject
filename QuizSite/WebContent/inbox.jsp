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

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<h1 class="inline">Inbox</h1>
<a class="inline right compose" href="sendMessage.jsp" class="inline"><img align="middle" class="composeIcon" src="http://gcseit.info/wp-content/uploads/2010/04/mozilla-compose-icon.png"/>Compose Message</a>
<ul class="inboxList">
	<% List<Message> messages = UserInfo.getMessages(username); %>
	<% if(messages.isEmpty()) %><li>Nothing yet!</li><%; %>
	<% for(Message cur : messages) {%>
		<li class="messagePanel"><%= cur.getHtml() %>
		<form action="DeleteMessageServlet" method="post" class="right inline">
           <input type="hidden" name="id" value="<%=cur.getMessageId() %>" />
           <input type="submit" value="Delete Message" class="removeButton"/>
     	</form>    
		</li>
	<% } %>
</ul>
</body>

</html>