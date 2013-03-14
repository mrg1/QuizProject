package question;

import java.util.*;

public class ResponseQuestion implements Question {

	public static final String DISPLAY_NAME = "Question-Response Question";
	
	private String question;
	private boolean caseSensitive;
	private int id, weight;
	private List<String> answers;
	
	public ResponseQuestion(String question, String[] answers) {
		this.question = question;
		weight = 1;
		caseSensitive = false;
		this.answers = Arrays.asList(answers);
	}
	
	public ResponseQuestion(String question, String[] answers, int weight) {
		this.question = question;
		this.weight = weight;
		caseSensitive = false;
		this.answers = Arrays.asList(answers);
	}
	
	public ResponseQuestion(String question, String[] answers, boolean caseSensitive, int weight) {
		this.question = question;
		this.weight = weight;
		this.caseSensitive = caseSensitive;
		this.answers = Arrays.asList(answers);
	}
	
	
	@Override
	public int checkAnswer(String userAnswer) {
		if(caseSensitive) {
			if(answers.contains(userAnswer.trim())) return weight;
		} else {
			for(String ans : answers) {
				if(ans.equalsIgnoreCase(userAnswer.trim())) return weight;
			}
		}
		return 0;
	}

	@Override
	public int getMaxScore() {
		return weight;
	}

	@Override
	public String getHTML() {
		String html = "<p>" + this.getText() + "</p>\n" + 
				"<p>Answer: <input type=\"text\" name=\"answer" + this.getID() + "\" /></p>";
		return html;
	}
	
	@Override
	public String getCorrectedHTML(String userAnswer) {
		String html = "<p>" + this.getText() + "</p>\n" + 
				"<p>Answer: <input type=\"text\" name=\"answer" + this.getID() + "\" value=\"" + userAnswer + "\" disabled /></p>";
		if(this.checkAnswer(userAnswer) == this.getMaxScore()) {
			html += "<p style=\"color: green; font-weight: bold\">Answer Correct!</p>";
		} else {
			html += "<p style=\"color: red; font-weight: bold\">Correct Answer(s): ";
			for(int i = 0; i < answers.size(); i++) {
				html += answers.get(i);
				if(i != (answers.size()-1)) html += ", ";
			}
			html += ".</p>";
		}
		return html;
	}
	
	@Override
	public int getID() {
		return id;
	}
	@Override
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}
	
	public static String getBuilderHTML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static ResponseQuestion parseXML(String XML) {
		//TODO
		return null;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setCaseSensitive(boolean bool) {
		caseSensitive = bool;
	}
	
	public boolean getCaseSensitive() {
		return caseSensitive;
	}
	
	public String getText() {
		return question;
	}
	
	public int getQuestionType(){
		return QuestionInfo.RESPONSE_QUESTION_ID;
	}
	
	public List<String> getCorrectAnswers(){
		List<String> result = answers;
		return result;
	}
	
	public List<String> getIncorrectAnswers(){
		List<String> result = new ArrayList<String>();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (caseSensitive ? 1231 : 1237);
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseQuestion other = (ResponseQuestion) obj;
		if (caseSensitive != other.caseSensitive)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	
}
