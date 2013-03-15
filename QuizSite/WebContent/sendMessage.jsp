<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Message</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body>

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<h1>Send Message</h1>
<form action="SendNoteServlet" method="post">
	<p>To: <input type="text" name="to"/></p>
	<p>Content: <textarea rows="4" cols="50" name="content"></textarea></p>
	<p><input type="submit" value="Send"/></p>
</form>
</body>

</html>