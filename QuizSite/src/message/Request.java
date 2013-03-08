package message;

import db.MessageInfo;

public class Request extends Message{
	
	public Request(String to, String from, String content) {
		super(to, from, content);
	}

	private static final int type = MessageInfo.REQUEST_ID;
	
	public String getHtml(){
		String out = "";
		out += "<p>" + from + " would like to be your friend. He/she says: "+content+"</p>\n";
		out += "<form action=\"AcceptServlet\" method=\"post\">\n";
		out += "<input type=\"hidden\" name=\"from\" value=\""+from+"\" />\n";
		out += "<input type=\"hidden\" name=\"id\" value=\""+getMessageId()+"\" />\n";
		out += "<input type=\"submit\" value=\"Accept\" />\n";
		out += "</form>";
		return out;
	}
	
	public int getType(){
		return type;
	}
	
}