package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserInfo;

/**
 * Servlet implementation class ClearQuizHistoryServlet
 */
@WebServlet("/ClearQuizHistoryServlet")
public class ClearQuizHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearQuizHistoryServlet() {
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
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		String username = (String)request.getSession().getAttribute("username");
		if(UserInfo.isAdmin(username)) {
			UserInfo.deleteHistoryForQuiz(quizID);
			request.setAttribute("alert", "History for " + UserInfo.getQuiz(quizID).getName() + " has been deleted.");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {
			request.setAttribute("alert", "Action not processed, you are not an admin.");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

}
