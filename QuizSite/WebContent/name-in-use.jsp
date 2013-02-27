<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Create Account</title>
</head>

<body>
<h1>The Name <%= request.getParameter("username") %> is Already In Use</h1>
<p>Please enter another name and password.</p>
<form action="CreateServlet" method="post">
<p>User Name: <input type="text" name="username" /></p>
<p>Password: <input type="password" name="password"/>
<input type="submit" value="Login"/></p>
</form>
</body>

</html>