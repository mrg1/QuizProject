package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserInfo;

import question.FillBlankQuestion;
import question.MultipleChoiceQuestion;
import question.PictureQuestion;
import question.QuestionInfo;
import question.ResponseQuestion;

/**
 * Servlet implementation class AddQuestionServlet
 */
@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestionServlet() {
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
		int quizId = Integer.parseInt(request.getParameter("quizId"));
		int currQuestionType = Integer.parseInt(request.getParameter("currQuestionType"));
		int nextQuestionType = Integer.parseInt(request.getParameter("nextQuestionType"));
		System.out.print(nextQuestionType);
		String nextPage = "create-question.jsp";
		String question;
		int weight;
		String boolString;
		boolean caseOrRand = false;
		String[] answers;
		switch(currQuestionType){
			case QuestionInfo.MULTIPLE_CHOICE_ID:
				boolString = request.getParameter("randomized");
				question = request.getParameter("question");
				weight = Integer.parseInt(request.getParameter("weight"));
				if(boolString != null){
					caseOrRand = true;
				}
				answers = QuestionInfo.getAnswersFromString(request.getParameter("choices"));
				String answer = request.getParameter("answer");
				UserInfo.addQuestion(quizId, new MultipleChoiceQuestion(question, answers, answer, caseOrRand, weight));
				break;
			case QuestionInfo.RESPONSE_QUESTION_ID:
				boolString = request.getParameter("caseSensitive");
				question = request.getParameter("question");
				weight = Integer.parseInt(request.getParameter("weight"));
				if(boolString != null){
					caseOrRand = true;
				}
				answers = QuestionInfo.getAnswersFromString(request.getParameter("answers"));
				UserInfo.addQuestion(quizId, new ResponseQuestion(question, answers, caseOrRand, weight));
				break;
			case QuestionInfo.PICTURE_QUESTION_ID:
				boolString = request.getParameter("caseSensitive");
				question = request.getParameter("question");
				weight = Integer.parseInt(request.getParameter("weight"));
				if(boolString != null){
					caseOrRand = true;
				}
				answers = QuestionInfo.getAnswersFromString(request.getParameter("answers"));
				UserInfo.addQuestion(quizId, new PictureQuestion(question, answers, caseOrRand, weight));
				break;
			case QuestionInfo.FILL_BLANK_ID:
				String pre = request.getParameter("pre");
				String post = request.getParameter("post");
				boolString = request.getParameter("caseSensitive");
				if(boolString != null){
					caseOrRand = true;
				}
				weight = Integer.parseInt(request.getParameter("weight"));
				answers = QuestionInfo.getAnswersFromString(request.getParameter("answers"));
				UserInfo.addQuestion(quizId, new FillBlankQuestion(pre, post, answers, caseOrRand, weight));
				break;
		}
		String htmlBuilder = "";
		switch(nextQuestionType){
			case -1:
				nextPage = "homepage.jsp";
				break;
			case QuestionInfo.MULTIPLE_CHOICE_ID:
				htmlBuilder = MultipleChoiceQuestion.getBuilderHTML();
				break;
			case QuestionInfo.FILL_BLANK_ID:
				htmlBuilder = FillBlankQuestion.getBuilderHTML();
				break;
			case QuestionInfo.PICTURE_QUESTION_ID:
				htmlBuilder = PictureQuestion.getBuilderHTML();
				break;
			case QuestionInfo.RESPONSE_QUESTION_ID:
				htmlBuilder = ResponseQuestion.getBuilderHTML();
				break;
		}	
		
		request.setAttribute("quizId", quizId);
		request.setAttribute("html", htmlBuilder);
		request.setAttribute("type", nextQuestionType);
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
