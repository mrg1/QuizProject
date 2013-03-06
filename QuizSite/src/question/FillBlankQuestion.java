package question;

import java.util.ArrayList;

public class FillBlankQuestion implements Question {

	private String question;
	private int weight;
	private boolean caseSensitive;
	private ArrayList<String> answers;
	private static final String BLANK = "_________";
	
	public FillBlankQuestion(String before, String after, String[] answers) {
		question = (before + BLANK + after);
		weight = 1;
		caseSensitive = false;
		this.answers = new ArrayList<String>();
		storeAnswers(answers);
	}
	
	public FillBlankQuestion(String before, String after, String[] answers, int weight) {
		question = (before + BLANK + after);
		this.weight = weight;
		caseSensitive = false;
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
	
	public String getText() {
		return question;
	}
	
	@Override
	public String getHTML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String getBuilderHTML() {
		// TODO Auto-generated method stub
		return null;
	}

}
