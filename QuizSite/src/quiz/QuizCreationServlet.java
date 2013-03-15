package quiz;

import java.io.IOException;
import java.util.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.FillBlankQuestion;
import question.MultipleChoiceQuestion;
import question.PictureQuestion;
import question.QuestionInfo;
import question.ResponseQuestion;

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
		if(name.equals("")) name = "{no name}";
		String description = request.getParameter("description");
		String tagString = request.getParameter("tags");
		String[] tags = QuestionInfo.getAnswersFromString(tagString);
		int questionType = Integer.parseInt(request.getParameter("questionType"));
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
		Set<String> tagSet = new HashSet<String>(Arrays.asList(tags));
		for(String tag : tagSet){
			UserInfo.addTag(q.getQuizId(), tag);
		}
		String htmlBuilder = "";
		switch(questionType){
			case QuestionInfo.MULTIPLE_CHOICE_ID:
				htmlBuilder = MultipleChoiceQuestion.getBuilderHTML();
				break;
			case QuestionInfo.PICTURE_QUESTION_ID:
				htmlBuilder = PictureQuestion.getBuilderHTML();
				break;
			case QuestionInfo.FILL_BLANK_ID:
				htmlBuilder = FillBlankQuestion.getBuilderHTML();
				break;
			case QuestionInfo.RESPONSE_QUESTION_ID:
				htmlBuilder = ResponseQuestion.getBuilderHTML();
				break;
		}
		
		request.setAttribute("quizId", q.getQuizId());
		request.setAttribute("html", htmlBuilder);
		request.setAttribute("type", questionType);
		RequestDispatcher dispatch = request.getRequestDispatcher("create-question.jsp");
		dispatch.forward(request, response);
	}

}