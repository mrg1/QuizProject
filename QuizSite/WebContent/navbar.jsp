<%@ page import="db.UserInfo" %>
<% boolean loggedIn = session.getAttribute("username")!=null; %>
<div class="navbarPanel">


<div class="right">
<ul class="navbar">
<li class="inline navbarItem"><a href="user.jsp?user=<%=(String)session.getAttribute("username")%>">Me</a></li>
<li class="inline navbarItem"><a href="tag-page.jsp">Categories</a></li>
<li class="inline navbarItem"><a href="quizzes.jsp">Quizzes</a></li>
<% if(loggedIn) { %>
<li class="inline navbarItem"><a href="friends.jsp">Friends</a></li>
<li class="inline navbarItem"><a href="inbox.jsp">Inbox</a></li>
<li class="inline navbarItem"><a href="create-quiz.jsp">Create Quiz</a></li>
<% if(UserInfo.isAdmin((String)session.getAttribute("username"))) { %>
	<li class="inline navbarItem"><a href="admin.jsp">Admin</a></li>
<% } %>
<li class="inline navbarItem logoutButton">
	<form action="LogoutServlet" method="post" class="inline">
		<p class="inline"><input type="submit" value="Logout" /></p>
	</form>
</li>
<% } else { %>
	<li class="inline navbarItem"><a href="index.jsp">Login</a></li>
<% } %>
</ul>
</div>

<div class="inline">
<p><a href="homepage.jsp"><img class="logo" src="http://imageshack.us/a/img7/1496/quizzsitelogo.png"></img></a></p>
</div>
</div>