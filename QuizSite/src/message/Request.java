package message;

import db.MessageInfo;

public class Request extends Message{
	
	public Request(String to, String from, String content) {
		super(to, from, content);
	}

	private static final int type = MessageInfo.REQUEST_ID;
	
	public String getHtml(){
		//TODO
		return "";
	}
	
	public int getType(){
		return type;
	}
	
}