<html>

<head>
<title>Welcome</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body>
<% if(request.getCookies() != null) { %>
<% for(Cookie cookie : request.getCookies()) { %>
	<% if(cookie.getName().equals("username") && cookie.getValue()!=null) { %>
		<% session.setAttribute("username",cookie.getValue()); %>
		<% response.sendRedirect("homepage.jsp"); %>
	<% } %>
<% } %>
<% } %>
<img class="centeredImage" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png">

<form class="centered" action="LoginServlet" method="post">
<p>User Name: <input type="text" name="username" /></p>
<p>Password: <input type="password" name="password"/></p>
<input class="loginButton" type="submit" value="Login"/>
</form>
<p class="centered"><a href="create-account.html">Create New Account</a></p>
</body>

</html>