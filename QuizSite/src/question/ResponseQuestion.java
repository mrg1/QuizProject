package question;

import java.util.*;

public class ResponseQuestion implements Question {

	public static final String DISPLAY_NAME = "Question-Response Question";
	
	private String question;
	private boolean caseSensitive;
	private int weight;
	private ArrayList<String> answers;
	
	public ResponseQuestion(String question, String[] answers) {
		this.question = question;
		weight = 1;
		caseSensitive = false;
		this.answers = new ArrayList<String>();
		storeAnswers(answers);
	}
	
	public ResponseQuestion(String question, String[] answers, int weight) {
		this.question = question;
		this.weight = weight;
		caseSensitive = false;
		this.answers = new ArrayList<String>();
		storeAnswers(answers);
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
}
