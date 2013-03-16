<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.*" %>
<%@ page import="quiz.*" %>
<%@ page import="message.*" %>
<%@ page import="question.*" %>
<%@ page import="java.util.*" %>

<head>
<% 
Quiz quiz = UserInfo.getQuiz((Integer) request.getAttribute("quizId"));
String username = (String)session.getAttribute("username"); 
Question[] questions = quiz.getQuestions();
String qOrder = (String) request.getAttribute("qOrder");
String[] idStrings = qOrder.split(",");
int[] idOrder = new int[idStrings.length];
for(int i = 0; i < idStrings.length; i++) {
	idOrder[i] = Integer.parseInt(idStrings[i]);
}
for(int i = 0; i < idStrings.length; i++) {
	questions[i] = UserInfo.getQuestion(idOrder[i]);
}

%>
<title><%= quiz.getName() %> Summary</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<h1><%=quiz.getName() %> Summary</h1>

<% Object from = request.getAttribute("from"); %>
<% if(from != null) { %>
	<h1>Challenge Results:</h1>
	<% int scoreToBeat = Integer.parseInt((String)request.getAttribute("scoreToBeat")); %>
	<% int percent = Integer.parseInt((String)request.getAttribute("percent")); %>
	<% if(scoreToBeat > percent) {%>
		<p>Sorry, you couldn't beat <%=(String)from %>'s score.</p>
		<% UserInfo.addMessage(new Note((String)from,username,"Shit I could not beat your score in "+quiz.getName()+".")); %>
	<% } else if(scoreToBeat < percent) { %>
		<p>You beat <%=(String)from %>'s score!</p>
		<% UserInfo.addMessage(new Note((String)from,username,"Beat yo ass on "+quiz.getName()+".")); %>
	<% } else { %>
		<p>You tied <%=(String)from %>'s score.</p>
		<% UserInfo.addMessage(new Note((String)from,username,"We tied on "+quiz.getName()+".")); %>
	<% } %>
	<% request.setAttribute("from",null); %>
	<% request.setAttribute("scoreToBeat",null); %>
<% } %>

<h1>You got <%= request.getAttribute("percent") %>%!!!</h1>
<table>
<tr>
<td>
<h2>Challenge a friend?</h2>
<form action="ChallengeServlet" method="post">
	<p>To: <input type="text" name="to"/></p>
	<p>Trash talk: <textarea rows="4" cols="50" name="content"></textarea>
	<input type="hidden" name="score" value=<%=request.getAttribute("percent") %> />
	<input type="hidden" name="quizID" value=<%=request.getAttribute("quizId") %> /></p>
	<p><input type="submit" value="Challenge"/></p>
</form>
</td>
<td>
<h2>Rate the quiz?</h2>
<form action="RateServlet" method="post">
	<p><select name="questionType">
	<option value="1">1 Star</option>
	<option value="2">2 Stars</option>
	<option value="3">3 Stars</option>
	<option value="4">4 Stars</option>
	<option value="5">5 Stars</option>
	</select></p>
	<p>Review: <textarea rows="4" cols="50" name="content"></textarea>
	<input type="hidden" name="quizID" value=<%=request.getAttribute("quizId") %> /></p>
	<p><input type="submit" value="Rate"/></p>
</form>
</td>
</tr>
</table>
<h2><%= request.getAttribute("elapsed") %> seconds elapsed.</h2>

<%



for(Question q : questions) {
	String ans = (String) request.getParameter("answer" + q.getID());
	out.println("<p></p><hr>");
	out.println(q.getCorrectedHTML(ans));
}
%>

<p class="quiz-buttons">
<button onclick="window.location = 'quiz.jsp?id=<%= quiz.getQuizId() %>'">Back To Quiz</button>
</p>

</body>

</html>
