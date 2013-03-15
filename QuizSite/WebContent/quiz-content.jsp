<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="quiz.*" %>
<%@ page import="question.*" %>
<%@ page import="java.util.*" %>

<head>
<% Quiz quiz = UserInfo.getQuiz(Integer.parseInt(request.getParameter("id"))); %>
<% String username = (String)session.getAttribute("username"); %>
<title><%= quiz.getName() %></title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<h1><%= quiz.getName() %></h1>

<form action="QuizServlet" method="post">
<input type="hidden" name="startTime" value="<%= System.currentTimeMillis() %>"></input>
<% 
Question[] questions = quiz.getQuestions();
/* int[] order = quiz.getQuestionOrder();
for(int i = 0; i < order.length; i++) {
	Question q = questions[order[i]];
	out.println("<hr>");
	out.println(q.getHTML());
	String paramInput = "<input type=\"hidden\" name=\"question" + i + "\" value=\"" + order[i] + "\" ></input>";
	System.out.println("Parameter question" + i + " = " + order[i]);
} */

for (Question q : questions) {
	out.println("<hr>");
	out.println(q.getHTML());
}
%>
<p>
<input type="hidden" name="quizId" value="<%= request.getParameter("id") %>"></input>
<% Object from = request.getAttribute("from"); %>
<% if(from != null) { %>
	<input type="hidden" name="scoreToBeat" value="<%= (String)request.getParameter("scoreToBeat") %>"></input>
	<input type="hidden" name="from" value="<%= (String)request.getParameter("from") %>"></input>
<% } %>
<input type="submit" value="Submit!"></input>
</p>
</form>



</body>

</html>
