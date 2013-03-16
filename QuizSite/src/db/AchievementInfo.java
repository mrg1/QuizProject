package db;

import java.util.List;

import quiz.Score;

public class AchievementInfo {
	public static final int PRACTICE_MAKES_ID = 0;
	public static final int AM_AUTH_ID = 1;
	public static final int PROF_AUTH_ID = 2;
	public static final int PROD_AUTH_ID = 3;
	public static final int QUIZ_MACHINE_ID = 4;
	public static final int THE_GREATEST_ID = 5;
	public static final int JAQUIZZ_ICAL_ID = 6;
	
	public static final int AM_AUTH_VALUE = 1;
	public static final int PROF_AUTH_VALUE = 5;
	public static final int PROD_AUTH_VALUE = 10;
	public static final int QUIZ_MACHINE_VALUE = 10;
	public static final int JAQUIZZ_ICAL_VALUE = 32;
	
	public static String getAchievement(int id) {
		switch(id) {
			case PRACTICE_MAKES_ID:
				return "Practice Makes Perfect!";
			case AM_AUTH_ID:
				return "Amateur Author!";
			case PROF_AUTH_ID:
				return "Prolific Author!";
			case PROD_AUTH_ID:
				return "Prodigious Author!";
			case QUIZ_MACHINE_ID:
				return "Quiz Machine!";
			case THE_GREATEST_ID:
				return "I am the Greatest!";
			case JAQUIZZ_ICAL_ID:
				return "Jacquizz would be proud!";
		}
		return "";
	}
	
	public static void checkQuizCreationAchievments(String username) {
		List<Integer> quizzes = UserInfo.getAuthorHistory(username);
		List<Integer> achs = UserInfo.getAchievements(username);
		if(quizzes.size() == AchievementInfo.AM_AUTH_VALUE && !achs.contains(AM_AUTH_ID)){
			UserInfo.addAchievment(username, AchievementInfo.AM_AUTH_ID);
		}
		if(quizzes.size() == AchievementInfo.PROF_AUTH_VALUE && !achs.contains(PROF_AUTH_ID)){
			UserInfo.addAchievment(username, AchievementInfo.PROF_AUTH_ID);
		}
		if(quizzes.size() == AchievementInfo.PROD_AUTH_VALUE && !achs.contains(PROD_AUTH_ID)){
			UserInfo.addAchievment(username, AchievementInfo.PROD_AUTH_ID);
		}
	}
	
	public static void checkQuizTakingAchievements(String username, int percent, int quizId, int elapsed) {
		List<Score> quizzes = UserInfo.getHistory(username);
		List<Integer> achs = UserInfo.getAchievements(username);
		List<Score> top = UserInfo.getTopTen(quizId);
		if (quizzes.size() >= QUIZ_MACHINE_VALUE && !achs.contains(QUIZ_MACHINE_ID)) {
			UserInfo.addAchievment(username, QUIZ_MACHINE_ID);
		}
		if(quizzes.size() >= JAQUIZZ_ICAL_VALUE && !achs.contains(JAQUIZZ_ICAL_ID)){
			UserInfo.addAchievment(username, JAQUIZZ_ICAL_ID);
		}
		if (top.size() > 0) {
			if ((top.get(0).getScore() < percent || (top.get(0).getScore() == percent && top.get(0).getElapsed() >= elapsed)) && !achs.contains(THE_GREATEST_ID)) {
				UserInfo.addAchievment(username, THE_GREATEST_ID);
			}
		} else {
			UserInfo.addAchievment(username, THE_GREATEST_ID);
		}
		
	}
	
	public static String getAchievementDesc(int id) {
		switch(id) {
			case PRACTICE_MAKES_ID:
				return "Took a quiz in practice mode";
			case AM_AUTH_ID:
				return "Created a quiz";
			case PROF_AUTH_ID:
				return "Created 5 quizzes";
			case PROD_AUTH_ID:
				return "Created 10 quizzes";
			case QUIZ_MACHINE_ID:
				return "Took 10 quizzes";
			case THE_GREATEST_ID:
				return "Earned top score on a quiz";
			case JAQUIZZ_ICAL_ID:
				return "Took 32 quizzes";
			}
		return "";
	}
	
}
