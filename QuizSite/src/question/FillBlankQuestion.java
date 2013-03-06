package question;

import java.util.ArrayList;

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
		return null;
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

}
