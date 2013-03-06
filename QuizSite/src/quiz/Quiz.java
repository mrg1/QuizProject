package quiz;

import user.User;
import question.Question;

/*
 * Extension Ideas:
 * 		Author (and/or administrator?) can go in and edit quiz after it's been made.
 */
public class Quiz {

	private String name, author, desc;
	private Question[] questions;
	private boolean random, onePage, immediateCorrection, practice;
	
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
	private Quiz(String name, String author, String description, Question[] questions, boolean random, boolean onePage, boolean immediateCorrection, boolean practice) {
		this.name = name;
		this.author = author;
		this.desc = description;
		this.questions = questions;
		this.random = random; 
		this.onePage = onePage; 
		this.immediateCorrection = immediateCorrection; 
		this.practice = practice;
	}
	
	public void remove() {
		//TODO
	}
	
	public void clearHistory() {
		//TODO
		//Delete scores out of table.
	}
	/**
	 * Retrieves a user's past scores on this quiz in the form of
	 * a two-dimensional array. 
	 * @param user
	 * @return A two-dimensional array containing a given record's score, 
	 * date, and time elapsed.
	 */
	public int[][] getUserScores(String user) {
		//TODO get out of scores table
		return null;
	}

	public String getName() {
		return name;
	}
	
	public String getAuthor() {
		return author;
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
	
	//EXTENSION IDEA: we can have an option for the author to let the user choose	
	public void setOnePage() {
		
	}

	public boolean immediateCorrection() {
		return immediateCorrection;
	}
	
	//same thing as setOnePage comment
	public void setImmediateCorrection() {
		
	}
	
	public boolean canPractice() {
		return practice;
	}
	
	public void setPractice(boolean bool) {
		practice = bool;
	}
}