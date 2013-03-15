package quiz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.AchievementInfo;
import db.UserInfo;

/**
 * Servlet implementation class QuizCreationServlet
 */
@WebServlet("/QuizCreationServlet")
public class QuizCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizCreationServlet() {
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
		boolean random = false;
		boolean onepage = true;
		boolean immediate = false;
		boolean practice = false;
		String randomValue = request.getParameter("random");
		String oneValue = request.getParameter("multipage");
		String immediateValue = request.getParameter("immediate");
		String practiceValue = request.getParameter("practice");
		String name = request.getParameter("quizname");
		String description = request.getParameter("description");
		if(randomValue!= null){
			random = true;
		}
		if(oneValue!= null){
			onepage = false;
		}
		if(immediateValue != null){
			immediate = true;
		}
		if(practiceValue != null){
			practice = true;
		}
		Quiz q = new Quiz(name, username, description, random, onepage, immediate, practice);
		UserInfo.addQuiz(q);
		AchievementInfo.checkQuizCreationAchievments(username);
	}

}
