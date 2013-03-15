package db;

import java.util.List;

public class AchievementInfo {
	public static final int PRACTICE_MAKES_ID = 0;
	public static final int AM_AUTH_ID = 1;
	public static final int PROF_AUTH_ID = 2;
	public static final int PROD_AUTH_ID = 3;
	public static final int QUIZ_MACHINE_ID = 4;
	public static final int THE_GREATEST_ID = 5;
	
	public static final int AM_AUTH_VALUE = 1;
	public static final int PROF_AUTH_VALUE = 5;
	public static final int PROD_AUTH_VALUE = 10;
	
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
}
