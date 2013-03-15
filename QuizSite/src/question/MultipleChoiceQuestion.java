package question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MultipleChoiceQuestion implements Question {

	public static final String DISPLAY_NAME = "Multiple Choice Question";
	
	private String question;
	private boolean randomize;
	private int id, weight;
	private List<String> choices;
	private String answer;
	
	public MultipleChoiceQuestion(String question, String[] choices, String answer) {
		this.question = question;
		weight = 1;
		randomize = false;
		this.choices = Arrays.asList(choices);
		this.answer = answer;
	}
	
	public MultipleChoiceQuestion(String question, String[] choices, String answer, int weight) {
		this.question = question;
		this.weight = weight;
		randomize = false;
		this.choices = Arrays.asList(choices);
		this.answer = answer;
	}
	
	public MultipleChoiceQuestion(String question, String[] choices, String answer, boolean randomize, int weight) {
		this.question = question;
		this.weight = weight;
		this.randomize = randomize;
		this.choices = Arrays.asList(choices);
		this.answer = answer;
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
	
	public List<String> getChoices() {
		return choices;
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
		String html = "<p>" + this.getText() + "</p>\n<p>";
		if(randomize) {
			Random rand = new Random();
			boolean[] used = new boolean[choices.size()];
			for(int i = 0; i < used.length; i++) {
				used[i] = false;
			}
			for(int j = 0; j < choices.size(); j++) {
				int index = rand.nextInt(choices.size());
				while(used[index]) {
					index = rand.nextInt(choices.size());
					if(allChecked(used)) break;
				}
				html += "<p><input type=\"radio\" name=\"answer" + this.getID() + "\" value=\"" 
						+ choices.get(index) +"\" />" + choices.get(index) + "<br />";
			}
		} else {
			for(int i = 0; i < choices.size(); i++) {
				html += "<p><input type=\"radio\" name=\"answer" + this.getID() + "\" value=\"" 
						+ choices.get(i) +"\" />" + choices.get(i) + "<br />";
			}
		}
		html += "</p>";
		return html;
	}
	
	public String getCorrectedHTML(String userAnswer) {
		String html = "<p>" + this.getText() + "</p>\n"; 
				for(int i = 0; i < choices.size(); i++) {
					html += "<p><input type=\"radio\" name=\"answer" + this.getID() + "\" value=\"" + choices.get(i) +"\" ";
					if(choices.get(i).equals(userAnswer)) html += "checked";
					html +=	" disabled />" + choices.get(i) + "<br />";
				};
		if(this.checkAnswer(userAnswer) == this.getMaxScore()) {
			html += "<p style=\"color: green; font-weight: bold\">Answer Correct!</p>";
		} else {
			html += "<p style=\"color: red; font-weight: bold\">Correct Answer: " + answer + "</p>";
		}
		return html;
	}
	
	private boolean allChecked(boolean[] used) {
		for(boolean bool : used) {
			if(!bool) return false; 
		}
		return true;
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}
	
	public static String getBuilderHTML() {
		String out = "";
		out += "<p>Question: <input type=\"text\" name=\"question\" /></p>\n";
		out += "<p>Incorrect Answers (separate each by new line): <textarea rows=\"4\" cols=\"50\" name=\"choices\"></textarea></p>\n";
		out += "<p>Correct Answer: <input type=\"text\" name=\"answer\" /></p>\n";
		out += "<p>Randomized order: <input type=\"checkbox\" name=\"randomized\" value=\"true\" /></p>\n";
		out += "<p>Weight: <input type=\"text\" name=\"weight\" />\n";
		out += "<input type=\"hidden\" name=\"questionId\" value=\""+QuestionInfo.MULTIPLE_CHOICE_ID+"\" /></p>\n"; 
		return out;
	}
	
	public static MultipleChoiceQuestion parseXML(String XML) {
		//TODO
		return null;
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
		List<String> result = new ArrayList<String>(choices);
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
