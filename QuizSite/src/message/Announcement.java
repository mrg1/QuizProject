package message;

public class Announcement {
	private final String content;
	private final String username;
	private final int id;
	
	public Announcement(String username, String content, int id){
		this.username = username;
		this.content = content;
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	
}
