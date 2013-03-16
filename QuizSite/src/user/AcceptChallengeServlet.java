package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.UserInfo;

/**
 * Servlet implementation class AcceptChallengeServlet
 */
@WebServlet("/AcceptChallengeServlet")
public class AcceptChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptChallengeServlet() {
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
		String from = request.getParameter("from");
		String scoreToBeat = request.getParameter("scoreToBeat");	
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("from",from);
		request.setAttribute("scoreToBeat",scoreToBeat);
		UserInfo.deleteMessages(id);
		if(UserInfo.getQuiz(quizID).isOnePage()) 
			request.getRequestDispatcher("quiz-content.jsp?id="+quizID).forward(request, response);
		else
			request.getRequestDispatcher("quiz-content-multipage.jsp?id="+quizID).forward(request, response);
	}

}
