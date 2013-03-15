<html>

<head>
<%-- <<% for(Cookie cookie : request.getCookies()) { %>
	<% if(cookie.getName().equals("username") && cookie.getValue()!=null) { %>
		<% session.setAttribute("username",cookie.getValue()); %>
		<% response.sendRedirect("homepage.jsp"); %>
	<% } %>
<% } %> --%>
<title>Welcome</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body>

<img class="centeredImage" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png">

<form class="centered" action="LoginServlet" method="post">
<table>
<tr><td>User Name:</td><td><input type="text" name="username" /></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" /></td></tr>
</table>
<input class="loginButton" type="submit" value="Login"/>
</form><br />
<form class="centered" action="create-account.html" method="post">
<input class="loginButton" type="submit" value="Create New Account"/>
</form>

</body>

</html>