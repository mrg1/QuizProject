package message;

import db.MessageInfo;

public class Note extends Message {
	
	public Note(String to, String from, String content) {
		super(to, from, content);
	}

	private static final int type = MessageInfo.NOTE_ID;
	
	public String getHtml(){
		//TODO
		return "";
	}
	
	public int getType(){
		return type;
	}
}
