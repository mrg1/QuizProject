package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import message.Challenge;
import db.UserInfo;

/**
 * Servlet implementation class ChallengeServlet
 */
@WebServlet("/ChallengeServlet")
public class ChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = (String)request.getSession().getAttribute("username");
		String to = request.getParameter("to");
		String content = request.getParameter("content");	
		String score = request.getParameter("score");
		String quizID = request.getParameter("quizID");
		
		if(UserInfo.userExists(to)) {
			UserInfo.addMessage(new Challenge(to,from,quizID+" "+score+" "+content));
			request.setAttribute("alert", "Challenge successfully sent!");
			request.getRequestDispatcher("quiz.jsp?id="+quizID).forward(request, response);
		} else {
			request.setAttribute("alert", "Challenge couldn't be sent, user " + to + " doesn't exist.");
			request.getRequestDispatcher("quiz.jsp?id="+quizID).forward(request, response);
		}
	}

}
