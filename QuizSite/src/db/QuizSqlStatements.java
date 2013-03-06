package db;

public class QuizSqlStatements {
	private final static String USER_TABLE = "users";
	private final static String USER_ID = "username";
	
	public final static String SQL_GET_USER = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "=?;";
	public final static String SQL_DELETE_USER = "DELETE FROM " + USER_TABLE + " WHERE username=?;";
	public final static String SQL_ADD_USER = "INSERT INTO " + USER_TABLE + " (username, password, salt, admin) VALUES (?, ?, ?, ?);";
	
	//Friend shit
	private final static String FRIENDS_TABLE = "friends";
	public final static String SQL_GET_FRIENDS = "SELECT friend FROM " + FRIENDS_TABLE + " WHERE " + "user=?;";
	public final static String SQL_ADD_FRIEND = "INSERT INTO " + FRIENDS_TABLE + " VALUES (?, ?);";
	
	
	//Acheivments
	private final static String ACHIEVMENTS_TABLE = "achievments";
	public final static String SQL_ADD_ACH = "INSERT INTO " + ACHIEVMENTS_TABLE + " (username, achievment_id) VALUES (?, ?);";
	public final static String SQL_GET_ACHS = "SELECT achievment_id FROM " + ACHIEVMENTS_TABLE + " WHERE username=?;";
	
	//Quizzes
	private final static String QUIZ_TABLE = "quizzes";
	public final static String SQL_ADD_QUIZ = "INSERT INTO " + QUIZ_TABLE + " (author, randomQuestions, multiPage, immediateCorrection, practiceMode) VALUES (?, ?, ?, ?, ?);";
	public final static String SQL_GET_QUIZ = "SELECT * FROM " + QUIZ_TABLE + " WHERE quizID =?;";
	public final static String SQL_GET_QUIZ_ID = "SELECT * FROM " + QUIZ_TABLE + " LAST_INSERT_ID;";
	
	//Messages
	private final static String MESSAGE_TABLE = "messages";
	public final static String SQL_ADD_MESSAGE = "INSERT INTO " +  MESSAGE_TABLE + " (userTo, userFrom, messageType, messageContent) VALUES (?, ?, ?, ?);";
	public final static String SQL_GET_MESSAGES = "SELECT * FROM " + MESSAGE_TABLE + " WHERE userTo=?;";
	public final static String SQL_GET_MESSAGE_ID = "SELECT * FROM " + MESSAGE_TABLE + " LAST_INSERT_ID;";
	public final static String SQL_DELETE_MESSAGE = "DELETE FROM " + MESSAGE_TABLE + " WHERE messageId =?;";
}