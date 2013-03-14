<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="quiz.*" %>
<%@ page import="question.*" %>
<%@ page import="java.util.*" %>

<head>
<% Quiz quiz = UserInfo.getQuiz((Integer) request.getAttribute("quizId")); %>
<% String username = (String)session.getAttribute("username"); %>
<title><%= quiz.getName() %> Summary</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<div class="navbarPanel">


<div class="right">
<ul class="navbar">
<li class="inline navbarItem"><a href="homepage.jsp">Home</a></li>
<li class="inline navbarItem"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline navbarItem"><a href="inbox.jsp">Inbox</a></li>
<li class="inline navbarItem logoutButton">
	<form action="LogoutServlet" method="post" class="inline">
		<p class="inline"><input type="submit" value="Logout" /></p>
	</form>
</li>
</ul>
</div>

<div class="inline">
<p><img class="logo" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png"></img></p>
</div>
</div>

<h1><%=quiz.getName() %> Summary</h1>

<h1>You got <%= request.getAttribute("percent") %>%!!!</h1>
<h2><%= request.getAttribute("elapsed") %> seconds elapsed.</h2>

<%
Question[] questions = quiz.getQuestions();
/* for(int i = 0; i < questions.length; i++) {
	int index = (Integer) request.getAttribute("question" + i);
	Question q = questions[index];
	String ans = (String) request.getAttribute("answer" + q.getID());
	out.println("<p></p><hr>");
	out.println(q.getCorrectedHTML(ans));
} */

for(Question q : questions) {
	String ans = (String) request.getAttribute("answer" + q.getID());
	out.println("<p></p><hr>");
	out.println(q.getCorrectedHTML(ans));
}
%>

<p class="quiz-buttons">
<button onclick="window.location = 'quiz.jsp?id=<%= quiz.getQuizId() %>'">Back To Quiz</button>
</p>

</body>

</html>
