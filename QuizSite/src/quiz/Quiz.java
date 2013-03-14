package quiz;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import db.UserInfo;

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

	private String name, author, desc, timestamp;
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
		UserInfo.addQuiz(this);
	}
	
	public Quiz(String name, String author, String description, Question[] questions, boolean random, boolean onePage, boolean immediateCorrection, boolean practice, int quizId) {
		this.name = name;
		this.author = author;
		this.desc = description;
		this.questions = questions;
		this.random = random; 
		this.onePage = onePage; 
		this.immediateCorrection = immediateCorrection; 
		this.practice = practice;
		this.quizId = quizId;
	}
	
	public void remove() {
		this.clearHistory();
		UserInfo.deleteQuiz(this.getQuizId());
	}
	
	public void clearHistory() {
		//TODO Delete scores out of table.
	}
	
	public void recordScore(String user, int score, int elapsed) {
		Score s = new Score(score, this.getQuizId(), user, elapsed);
		UserInfo.addScore(s);
	}
	
	public List<Score> getUserHistory(String user) {
		return UserInfo.getUserHistoryOnQuiz(user, this.getQuizId());
	}
	
	public void clearUserHistory(String user) {
		UserInfo.deleteUserHistoryOnQuiz(user, this.getQuizId());
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
	
	/**
	 * 
	 * @return an array listing the questions[] index in the order they should be presented
	 * (i.e. [3, 1, 2] means questions[3] then questions[1] then questions[2])
	 */
	public int[] getQuestionOrder() {
		int[] result = new int[questions.length];
		if(random) {
			Random rand = new Random();
			boolean[] used = new boolean[result.length];
			for(int i = 0; i < used.length; i++) {
				used[i] = false;
			}
			for(int j = 0; j < result.length; j++) {
				int index = rand.nextInt(result.length);
				while(used[index]) {
					index = rand.nextInt(result.length);
					if(allChecked(used)) break;
				}
				result[index] = j;
			}
			
		} else {
			for(int i = 0; i < result.length; i++) result[i] = i;
		}
		return result;
	}
	
	private boolean allChecked(boolean[] used) {
		for(boolean bool : used) {
			if(!bool) return false; 
		}
		return true;
	}
	
	/**
	 * 
	 * @return Date quiz was created in yyyy-mm-dd format
	 */
//	public String getDateCreated() {
//		return null;
//	}
	
	public boolean getRandom() {
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