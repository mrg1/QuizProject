package db;

public class AchievementInfo {
	public static final int PRACTICE_MAKES_ID = 0;
	public static final int AM_AUTH_ID = 1;
	public static final int PROF_AUTH_ID = 2;
	public static final int PROD_AUTH_ID = 3;
	public static final int QUIZ_MACHINE_ID = 4;
	public static final int THE_GREATEST_ID = 5;
	
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
	
}
