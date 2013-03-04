package question;

/**
 * Question Interface to be implemented by the various question types.
 * Can support questions with varied weights and partial credit.
 */

public interface Question {
	
	/**
	 * Scores the user's answer and returns the score. This will be 1 for
	 * simple questions, but could be used for varied weight per question
	 * and/or partial credit.
	 * @param userAnswer User's submitted answer as String
	 * @return score Points earned with answer
	 */
	int checkAnswer(String userAnswer);
	
	/**
	 * @return Maximum score for the question.
	 */
	int getMaxScore();
	
	/**
	 * @return HTML representation of the question for quiz view.
	 */
	String getHTML();
}
