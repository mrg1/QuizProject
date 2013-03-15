package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import question.*;
import db.AchievementInfo;
import db.UserInfo;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
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
		int elapsed = (int) ((System.currentTimeMillis() - (Long.parseLong(request.getParameter("startTime"))))/1000);
		String username = (String) request.getSession().getAttribute("username");
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		request.setAttribute("quizId", quizId);
		Quiz quiz = UserInfo.getQuiz(quizId);
		int score = 0;
		int maxScore = 0;
		for(Question question : quiz.getQuestions()) {
			int questionId = question.getID();
			String ans = request.getParameter("answer" + questionId);
			score += question.checkAnswer(ans);
			request.setAttribute("answer" + questionId, ans);
			maxScore += question.getMaxScore();
		}
		int percent = (score*100)/maxScore;
		quiz.recordScore(username, percent, elapsed);
		AchievementInfo.checkQuizTakingAchievements(username, percent, quizId, elapsed);
		request.setAttribute("percent", Integer.toString(percent));
		request.setAttribute("elapsed", Integer.toString(elapsed));
		String from = request.getParameter("from");
		String scoreToBeat = request.getParameter("scoreToBeat");
		if(from != null) {
			request.setAttribute("from",from);
			request.setAttribute("scoreToBeat",scoreToBeat);
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("quiz-summary.jsp");
		dispatch.forward(request, response);
	}

}
