package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quiz.Rating;
import db.UserInfo;

/**
 * Servlet implementation class RateServlet
 */
@WebServlet("/RateServlet")
public class RateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateServlet() {
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
		String username = (String) request.getSession().getAttribute("username");
		int rating = Integer.parseInt(request.getParameter("questionType"));
		String content = request.getParameter("review");
		int quizId = Integer.parseInt(request.getParameter("quizID"));
		UserInfo.addRating(new Rating(username,quizId,rating,content));
		request.setAttribute("alert","Review added!");
		request.getRequestDispatcher("quiz.jsp?id="+quizId).forward(request, response);
	}

}
