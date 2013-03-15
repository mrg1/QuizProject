<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.UserInfo" %>
<%@ page import="java.util.ArrayList" %>

<head>
<title>Friends</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<div class="friendPanel">
<form action="SendRequestServlet" method="post">
	<p class="inline friendInput">Friend Name: <input type="text" name="to" class="inputText" /></p>
	<p class="inline friendInput">Message(optional): <input type="text" name="content" class="inputText" /></p>
	<p class="inline friendInput"><input type="submit" value="Add" /></p>
</form>
</div>

<hr>


<table class="friendTable">
<tr>
<th>Friend</th>
<th></th>
</tr>


<% ArrayList<String> friends = UserInfo.getFriends((String) session.getAttribute("username")); %>
<% if(friends.isEmpty()) %><tr><td>You have no friends.</td></tr>
<% for(String friend : friends) { %>
<tr>
<td align="center"><a href="user.jsp?user=<%=friend%>"><%= friend %></a></td>
<td align="center">
	<form action="DeleteFriendServlet" method="post">
    	<p><input type="hidden" name="friend" value=<%=friend %> />
        <input type="submit" value="Remove" /></p>
    </form></td>
</tr>

<%} %>

</table>

</body>

</html>