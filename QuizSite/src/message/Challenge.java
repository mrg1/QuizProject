package message;

import db.MessageInfo;
import db.UserInfo;

public class Challenge extends Message {
	private int score;
	private int quizID;
	public Challenge(String to, String from, String content, int score, int quizID) {
		super(to, from, content);
		this.score = score;
		this.quizID = quizID;
	}

	private static final int type = MessageInfo.CHALLENGE_ID;
	
	public String getHtml(){
		String out = "";
		out += "<p>" + from + " received a score of "+score+" on the quiz "+UserInfo.getQuiz(quizID).getName()+". You gonna take that?";
		out += "He/she says: "+content+"</p>\n";
		out += "</form>";
		return out;
	}
	
	public int getType(){
		return type;
	}
}
