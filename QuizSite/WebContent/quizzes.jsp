<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Quizzes</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline"><a href="inbox.jsp">Inbox</a></li>
<li class="inline">
	<form action="LogoutServlet" method="post" class="inline">
		<input type="submit" value="Logout" />
	</form>
</li>
</ul>

<h1>All Quizzes</h1>

<table class="table4">
<tr>
<th>Quiz Title</th>
<th>Created By</th>
<th>Date</th>
</tr>
<tr>
<td><a href="quiz.jsp">Title 1</a></td>
<td>User 1</td>
<td>Date 1</td>
</tr>
<tr>
<td>Title 2</td>
<td>User 2</td>
<td>Date 2</td>
</tr>
<tr>
<td>Title 3</td>
<td>User 3</td>
<td>Date 3</td>
</tr>
</table>


</body>

</html>