package message;

import db.MessageInfo;

public class Request extends Message{
	
	public Request(String to, String from, String content) {
		super(to, from, content);
	}

	private static final int type = MessageInfo.REQUEST_ID;
	
	public String getHtml(){
		String out = "";
		out += "<p><a href='user.jsp?user="+from+"'>"+from+"</a> would like to be your friend.";
		if(!content.isEmpty()) out += " He/she says: "+content;
		out += "</p>\n";
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