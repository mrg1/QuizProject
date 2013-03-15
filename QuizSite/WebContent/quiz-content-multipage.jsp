<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page import="db.*" %>
<%@ page import="quiz.*" %>
<%@ page import="question.*" %>
<%@ page import="java.util.*" %>

<head>
<% Quiz quiz = UserInfo.getQuiz(Integer.parseInt(request.getParameter("id"))); %>
<% String username = (String)session.getAttribute("username"); %>
<% int questionIndex = Integer.parseInt(request.getParameter("q")); %>
<title><%= quiz.getName() %></title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>

<h1><%= quiz.getName() %></h1>
<hr>

<% 	
if(questionIndex < (quiz.getQuestions().length-1)) { //not last question
	out.println("<form action=\"quiz-content-multipage.jsp?id="+quiz.getQuizId()+"&q="+ (questionIndex+1) +"\" method=\"post\"");
} else { //last question --> servlet
	out.println("<form action=\"QuizServlet\" method=\"post\"");
}
if(questionIndex==0) {
	request.setAttribute("startTime", System.currentTimeMillis());
} else {
	request.setAttribute("startTime", request.getAttribute("startTime"));
}

Question[] questions = quiz.getQuestions();

for(Question ques : questions) {
	if(request.getParameter("answer"+ques.getID()) != null) {
		out.println("<input type=\"hidden\" name=\"answer"+ques.getID()+"\" value="+request.getParameter("answer"+ques.getID())+"></input>");
	} 
}

%>
<%= questions[questionIndex].getHTML() %>
<input type="hidden" name="quizId" value="<%= request.getParameter("id") %>"></input> 
<%
if(questionIndex < (quiz.getQuestions().length-1)) { //not last question
	out.println("<input type=\"submit\" value=\"Next Question\"></input>");
} else { //last question --> servlet
	out.println("<input type=\"submit\" value=\"Submit!\"></input>");
}
%>
</form>



</body>

</html>