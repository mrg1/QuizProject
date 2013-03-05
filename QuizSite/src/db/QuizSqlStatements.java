package db;

public class QuizSqlStatements {
	private final static String USER_TABLE = "users";
	private final static String USER_ID = "username";
	
	public final static String SQL_GET_USER = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "=?;";
	public final static String SQL_ADD_USER = "INSERT INTO " + USER_TABLE + " VALUES (?, ?);";
	
	//Friend shit
	private final static String FRIENDS_TABLE = "friends";
	public final static String SQL_GET_FRIENDS = "SELECT * FROM " + FRIENDS_TABLE + " WHERE " + "user=?;";
	public final static String SQL_ADD_FRIEND = "INSERT INTO " + FRIENDS_TABLE + " VALUES (?, ?);";
}
