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
		achievments = new ArrayList<String>();
		friends = new ArrayList<User>();
		//DB.addUser(this);
	}
	
}
