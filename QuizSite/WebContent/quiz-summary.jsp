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

<h3 class="inline title">Awesome Quiz Site</h3>

<ul class="navbar">
<li class="inline"><a href="homepage.jsp">Home</a></li>
<li class="inline"><a href="quizzes.jsp">Quizzes</a></li>
<li class="inline"><a href="about.asp">Messages</a></li>
</ul>

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