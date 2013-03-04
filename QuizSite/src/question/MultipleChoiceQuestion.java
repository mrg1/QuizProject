package question;

import java.util.ArrayList;

public class MultipleChoiceQuestion implements Question {

	private String question;
	private boolean randomize;
	private int weight;
	private ArrayList<String> answers;
	private String answer;
	
	public MultipleChoiceQuestion(String question, String[] choices, String answer) {
		this.question = question;
		weight = 1;
		randomize = false;
		this.answers = new ArrayList<String>();
		this.answer = answer;
		storeAnswers(choices);
	}
	
	public MultipleChoiceQuestion(String question, String[] choices, String answer, int weight) {
		this.question = question;
		this.weight = weight;
		randomize = false;
		this.answers = new ArrayList<String>();
		this.answer = answer;
		storeAnswers(choices);
	}
	
	@Override
	public int checkAnswer(String userAnswer) {
		if(userAnswer.equals(answer)) return weight;
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

	public static String getBuilderHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	private void storeAnswers(String [] choices) {
		answers.clear();
		for(int i = 0; i < choices.length; i++) {
			answers.add(choices[i].toLowerCase());
		}
		if(!answers.contains(answer)) {
			System.err.println("Answer is not one of the choices. This question will now be impossible to answer correctly.");
		}
	}
}
