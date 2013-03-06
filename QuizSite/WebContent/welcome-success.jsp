<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<%@ page import="db.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Welcome <%= request.getParameter("username") %></title>
</head>

<body>
<h1>Welcome <%= request.getParameter("username") %></h1>
<p>Friends:</p>
<ul>
	<%for(String cur : UserInfo.getFriends(request.getParameter("username"))) { %>
		<li><%=cur %></li>
	<%} %>
</ul>
</body>

</html>