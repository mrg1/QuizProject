package question;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion implements Question {

	public static final String DISPLAY_NAME = "Multiple Choice Question";
	
	private String question;
	private boolean randomize;
	private int id, weight;
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
	
	public MultipleChoiceQuestion(String question, String[] choices, String answer, boolean randomize, int weight) {
		this.question = question;
		this.weight = weight;
		this.randomize = randomize;
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
	
	public String getText() {
		return question;
	}
	
	public String[] getChoices() {
		return (String[]) answers.toArray();
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setRandom(boolean bool) {
		randomize = bool;
	}
	
	public boolean getRandom() {
		return randomize;
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
	
	public static MultipleChoiceQuestion parseXML(String XML) {
		//TODO
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
	
	public int getQuestionType(){
		return QuestionInfo.MULTIPLE_CHOICE_ID;
	}
	
	public List<String> getCorrectAnswers(){
		List<String> result = new ArrayList<String>();
		result.add(answer);
		return result;
	}
	
	public List<String> getIncorrectAnswers(){
		List<String> result = new ArrayList<String>(answers);
		result.remove(answer);
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + (randomize ? 1231 : 1237);
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
		MultipleChoiceQuestion other = (MultipleChoiceQuestion) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (randomize != other.randomize)
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
