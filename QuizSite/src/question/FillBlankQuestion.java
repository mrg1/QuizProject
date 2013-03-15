package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FillBlankQuestion implements Question {

	public static final String DISPLAY_NAME = "Fill in the Blank Question";
	
	private String pre, post;
	private int id, weight;
	private boolean caseSensitive;
	private List<String> answers;
	
	public FillBlankQuestion(String pre, String post, String[] answers) {
		this.pre = pre.trim();
		this.post = post.trim();
		weight = 1;
		caseSensitive = false;
		this.answers = Arrays.asList(answers);
	}
	
	public FillBlankQuestion(String pre, String post, String[] answers, boolean sensitive) {
		this.pre = pre.trim();
		this.post = post.trim();
		weight = 1;
		caseSensitive = sensitive;
		this.answers = Arrays.asList(answers);
	}
	
	public FillBlankQuestion(String pre, String post, String[] answers, boolean sensitive, int weight) {
		this.pre = pre.trim();
		this.post = post.trim();
		this.weight = weight;
		caseSensitive = sensitive;
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
	
	public String getPre() {
		return pre;
	}
	
	public String getPost() {
		return post;
	}
	
	@Override
	public String getHTML() {
		String html = "<p>" + this.getPre() + " ________ " + this.getPost() + "</p>\n" + 
				"<p>Answer: <input type=\"text\" name=\"answer" + this.getID() + "\" /></p>";
		return html;
	}
	
	@Override
	public String getCorrectedHTML(String userAnswer) {
		String html = "<p>" + this.getPre() + " ________ " + this.getPost() + "</p>\n" + 
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
		out += "<p>Question: <input type=\"text\" name=\"pre\" />________<input type=\"text\" name=\"post\" /><\n";
		out += "<p>Answers (separate each by new line): <textarea rows=\"4\" cols=\"50\" name=\"answers\"></textarea></p>\n";
		out += "<p>Case Sensitive: <input type=\"checkbox\" name=\"caseSensitive\" value=\"true\" /></p>\n";
		out += "<p>Weight: <input type=\"text\" name=\"weight\" />\n";
		out += "<input type=\"hidden\" name=\"questionId\" value=\""+QuestionInfo.FILL_BLANK_ID+"\" /></p>\n"; 
		return out;
	}
	
	public static FillBlankQuestion parseXML(String XML) {
		//TODO
		return null;
	}

	public int getQuestionType(){
		return QuestionInfo.FILL_BLANK_ID;
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
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((pre == null) ? 0 : pre.hashCode());
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
		FillBlankQuestion other = (FillBlankQuestion) obj;
		if (caseSensitive != other.caseSensitive)
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (pre == null) {
			if (other.pre != null)
				return false;
		} else if (!pre.equals(other.pre))
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
