package question;

import java.util.*;

public class QuestionInfo {
	public static final int MULTIPLE_CHOICE_ID = 0;
	public static final int PICTURE_QUESTION_ID = 1;
	public static final int FILL_BLANK_ID = 2;
	public static final int RESPONSE_QUESTION_ID = 3;
	
	
	public static String[] getAnswersFromString(String answers) {
		List<String> result = new ArrayList<String>();
		String[] tokens = answers.split("\n");
		for(String s : tokens){
			result.add(s);
		}
		String[] arrayResult = result.toArray(new String[result.size()]);
		return arrayResult;
	}
}