package question;

import java.util.ArrayList;
import java.util.List;

public class FillBlankQuestion implements Question {

	public static final String DISPLAY_NAME = "Fill in the Blank Question";
	
	private String pre, post;
	private int weight;
	private boolean caseSensitive;
	private ArrayList<String> answers;
	
	public FillBlankQuestion(String pre, String post, String[] answers, boolean sensitive) {
		this.pre = pre;
		this.post = post;
		weight = 1;
		caseSensitive = sensitive;
		this.answers = new ArrayList<String>();
		storeAnswers(answers);
	}
	
	public FillBlankQuestion(String pre, String post, String[] answers, boolean sensitive, int weight) {
		this.pre = pre;
		this.post = post;
		this.weight = weight;
		caseSensitive = sensitive;
		this.answers = new ArrayList<String>();
		storeAnswers(answers);
	}
	
	private void storeAnswers(String[] ans) {
		answers.clear();
		for(int i = 0; i < ans.length; i++) {
			if(caseSensitive) {
				answers.add(ans[i]);
			} else {
				answers.add(ans[i].toLowerCase());
			}
		}
	}

	@Override
	public int checkAnswer(String userAnswer) {
		if(caseSensitive) {
			if(answers.contains(userAnswer)) return weight;
		} else {
			if(answers.contains(userAnswer.toLowerCase())) return weight;
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
		// TODO Auto-generated method stub
		return "test2";
	}
	
	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}
	
	public static String getBuilderHTML() {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
