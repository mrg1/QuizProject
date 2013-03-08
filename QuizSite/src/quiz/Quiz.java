package quiz;

import java.util.Arrays;

import question.Question;

/*
 * Database Commands needed:
 * addQuiz(Quiz quiz)
 * getQuiz(String id)
 * removeQuiz(String id)
 * clearHistory(String username)
 * getUserHistory(String username) //from scores table
 * recordScore(String username, int score) //how do we represent timestamp? 
 * 
 * Extension Ideas:
 * Author (and/or administrator?) can go in and edit quiz after it's been made.
 * XML import
 * 
 */
public class Quiz {

	private String name, author, desc;
	private Question[] questions;
	private boolean random, onePage, immediateCorrection, practice;
	private int quizId;
	/**
	 * Constructor for a new quiz. 
	 * @param name Quiz name
	 * @param author Username of quiz author
	 * @param description Author's description of the quiz
	 * @param questions Array of question objects
	 * @param random TRUE for randomized ordering of questions, FALSE maintains the order in the questions array.
	 * @param onePage TRUE for all questions on one page, FALSE for one question per page.
	 * @param immediateCorrection TRUE for immediate correction of questions. Correction will only take place if onePage is FALSE.
	 * @param practice TRUE indicates practice mode is available, FALSE disables practice.
	 */
	public Quiz(String name, String author, String description, Question[] questions, boolean random, boolean onePage, boolean immediateCorrection, boolean practice) {
		this.name = name;
		this.author = author;
		this.desc = description;
		this.questions = questions;
		this.random = random; 
		this.onePage = onePage; 
		this.immediateCorrection = immediateCorrection; 
		this.practice = practice;
		//TODO add to table
	}
	
	public void remove() {
		//TODO deletes quiz (including score history) from table
		this.clearHistory();
	}
	
	public void clearHistory() {
		//TODO Delete scores out of table.
	}
	
	public void recordScore(String user, int score) {
		//TODO add score to table
	}
	
	/**
	 * Retrieves a user's past scores on this quiz in the form of
	 * a two-dimensional array. 
	 * @param user
	 * @return A two-dimensional array containing a given record's score, 
	 * date, and time elapsed.
	 */
	public int[][] getUserHistory(String user) {
		//TODO get out of scores table
		return null;
	}
	
	public static Quiz parseXML(String XML) {
		return null;
	}

	public String getName() {
		return name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public Question[] getQuestions() {
		return questions;
	}
	
	public boolean isRandom() {
		return random;
	}
	
	public void setRandom(boolean rand) {
		random = rand;
	}
	
	public boolean isOnePage() {
		return onePage;
	}
	
	public void setOnePage(boolean bool) {
		onePage = bool;
	}

	public boolean immediateCorrection() {
		return immediateCorrection;
	}
	
	public void setImmediateCorrection(boolean bool) {
		immediateCorrection = bool;
	}
	
	public boolean canPractice() {
		return practice;
	}
	
	public void setPractice(boolean bool) {
		practice = bool;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + (immediateCorrection ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (onePage ? 1231 : 1237);
		result = prime * result + (practice ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(questions);
		result = prime * result + quizId;
		result = prime * result + (random ? 1231 : 1237);
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
		Quiz other = (Quiz) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (immediateCorrection != other.immediateCorrection)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (onePage != other.onePage)
			return false;
		if (practice != other.practice)
			return false;
		if (!Arrays.equals(questions, other.questions))
			return false;
		if (quizId != other.quizId)
			return false;
		if (random != other.random)
			return false;
		return true;
	}

	




}