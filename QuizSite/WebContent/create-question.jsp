<?xml version="1.0" encoding="UTF-8" ?><!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page import="db.UserInfo" %>

<head>
<title>Add Question</title>
<link href="stylesheet.css" rel="stylesheet" type="text/css"></link>
</head>

<body>

<%@include file="navbar.jsp" %>


<% Object message = request.getAttribute("alert"); %>
<% if(message != null) { %>
	<p><%=(String)message %></p>
	<% request.setAttribute("alert",null); %>
<% } %>

<div class="centered">
<h1>Add New Question</h1>
<p>Please enter the information for question:</p>
<form action="AddQuestionServlet" method="post">

<%=(String)request.getAttribute("html")%>

<p><input type="submit" value="Add New Question"/> 
<input type="hidden" name="quizId" value="<%=request.getAttribute("quizId")%>"/>
<input type="hidden" name="currQuestionType" value="<%=request.getAttribute("type")%>"/>
<select name="nextQuestionType">
<option value="-1">Done Creating Quiz</option>
<option value="0">Multiple Choice</option>
<option value="1">Picture Question</option>
<option value="3">Response Question</option>
<option value="2">Fill in the Blank Question</option>
</select>
</p>
</form>
</div>

</body>

</html>