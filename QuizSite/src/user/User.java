package user;

import java.util.*;

public class User {
	private String username;
	private String password;
	private List<User> friends;
	private List<String> achievments;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
		this.achievments = new ArrayList<String>();
		this.friends = new ArrayList<User>();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<String> getAchievments() {
		return achievments;
	}

	public void setAchievments(List<String> achievments) {
		this.achievments = achievments;
	}

}
