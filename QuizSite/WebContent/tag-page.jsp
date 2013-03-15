<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="db.*" %>
<%@ page import="message.*" %>
<%@ page import="java.util.List" %>
<head>
<% String username = (String)session.getAttribute("username"); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tag Page</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body>

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<h1>Tags</h1>

<ul class="list">
	<% List<String> tags = UserInfo.getAllTags(); %>
	<% if(tags.isEmpty()) %><li>No Tags Available</li><%; %>
	<% for(String tag : tags) {%>
		<li>
		<form action="display-quizzes-for-tag.jsp" method="post">
		<input type="hidden" name="tagType" value="<%=tag%>"/>
		<input type="submit" class="submitLink" value="<%=tag%>"/>
		</form>   
		</li>
	<% } %>
</ul>
</body>

</html>