package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PictureQuestion implements Question {

	public static final String DISPLAY_NAME = "Picture-Response Question";
	private String url;
	private int id, weight;
	private boolean caseSensitive;
	List<String> answers;
	
	public PictureQuestion(String url, String[] answers) {
		this.url = url;
		this.weight = 1;
		caseSensitive = false;
		this.answers = Arrays.asList(answers);
	}
	
	public PictureQuestion(String url, String[] answers, int weight) {
		this.url = url;
		this.weight = weight;
		caseSensitive = false;
		this.answers = Arrays.asList(answers);
	}
	
	public PictureQuestion(String url, String[] answers, boolean caseSensitive, int weight) {
		this.url = url;
		this.weight = weight;
		this.caseSensitive = caseSensitive;
		this.answers = Arrays.asList(answers);
	}
	
	public boolean getCaseSensitive() {
		return caseSensitive;
	}
	
	public void setCaseSensitive(boolean bool) {
		caseSensitive = bool;
	}
	
	public String getPictureURL() {
		return url;
	}
	
	public void setPictureURL(String str) {
		url = str;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int w) {
		weight = w;
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
		String html = "<img class=\"testPicture\" src=\"" + url + "\" alt=\"Picture-Response Question\">\n";
		html += "<p>Answer: <input type=\"text\" name=\"answer" + this.getID() + "\" /></p>";
		return html;
	}
	
	public String getCorrectedHTML(String userAnswer) {
		String html = "<img class=\"testPicture\" src=\"" + url + "\" alt=\"Picture-Response Question\">\n" + 
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
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	public static String getBuilderHTML() {
		String out = "";
		out += "<p>Image URL: <input type=\"text\" name=\"question\" /></p>\n";
		out += "<p>Answers (separate each by new line): <textarea rows=\"4\" cols=\"50\" name=\"answers\"></textarea></p>\n";
		out += "<p>Case Sensitive: <input type=\"checkbox\" name=\"caseSensitive\" value=\"true\" /></p>\n";
		out += "<p>Weight: <input type=\"text\" name=\"weight\" />\n";
		out += "<input type=\"hidden\" name=\"questionId\" value=\""+QuestionInfo.PICTURE_QUESTION_ID+"\" /></p>\n"; 
		return out;
	}
	
	public static PictureQuestion parseXML(String XML) {
		//TODO
		return null;
	}
	
	public int getQuestionType(){
		return QuestionInfo.PICTURE_QUESTION_ID;
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
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		PictureQuestion other = (PictureQuestion) obj;
		if (caseSensitive != other.caseSensitive)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	@Override
	public int getID() {
		return id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}
	
}
