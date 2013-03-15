package message;

import db.MessageInfo;
import db.UserInfo;

public class Challenge extends Message {
	public Challenge(String to, String from, String content) {
		super(to,from,content);
	}

	private static final int type = MessageInfo.CHALLENGE_ID;
	
	public String getHtml(){
		String realContent = content;
		int firstSpace = realContent.indexOf(" ");
		int quizID = Integer.parseInt(realContent.substring(0,firstSpace));
		realContent = realContent.substring(firstSpace+1);
		int secondSpace = realContent.indexOf(" ");
		String score = realContent.substring(0,secondSpace);
		realContent = realContent.substring(secondSpace+1);

		String out = "";
		out += "<p>" + from + " received a score of "+score+"% on "+UserInfo.getQuiz(quizID).getName() + ". Whatchu got?</p>\n";
		out += "<p>He/she says: "+realContent+"</p>\n";
		out += "<form action=\"AcceptChallengeServlet\" method=\"post\">\n";
		out += "<input type=\"hidden\" name=\"from\" value=\""+from+"\" />\n";
		out += "<input type=\"hidden\" name=\"scoreToBeat\" value=\""+score+"\" />\n";
		out += "<input type=\"hidden\" name=\"quizID\" value=\""+quizID+"\" />\n";
		out += "<input type=\"hidden\" name=\"id\" value=\""+getMessageId()+"\" />\n";
		out += "<input type=\"submit\" value=\"Accept\" />\n";
		out += "</form>";
		return out;
	}
	
	public int getType(){
		return type;
	}
}
