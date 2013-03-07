package message;

import db.MessageInfo;

public class Note extends Message {
	
	public Note(String to, String from, String content) {
		super(to, from, content);
	}

	private static final int type = MessageInfo.NOTE_ID;
	
	public String getHtml(){
		String out = "";
		out += "<p>From: " + from + "</p>\n";
		out += "<p>" + content + "</p>";
		return out;
	}
	
	public int getType(){
		return type;
	}
}
