package message;

import db.MessageInfo;

public class Challenge extends Message {
	
	public Challenge(String to, String from, String content) {
		super(to, from, content);
	}

	private static final int type = MessageInfo.CHALLENGE_ID;
	
	public String getHtml(){
		//TODO
		return "";
	}
	
	public int getType(){
		return type;
	}
}
