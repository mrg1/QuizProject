<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Create Account</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<img class="centeredImage" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png">

<div class="centered">
<h1>The Name <%=request.getParameter("username") %> is Already In Use</h1>
<p>Please enter another name and password.</p>
<form action="CreateServlet" method="post">
<p>User Name: <input type="text" name="username" /></p>
<p>Password: <input type="password" name="password"/></p>
<input class="loginButton" type="submit" value="Create Account"/>
</form>

<form class="centered" action="index.jsp" method="post">
<input class="loginButton" type="submit" value="Return To Login"/>
</form>
</div>
</body>

</html>