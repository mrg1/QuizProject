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
	public final static String SQL_ADD_QUIZ = "INSERT INTO " + QUIZ_TABLE + " (name, author, description, randomQuestions, multiPage, immediateCorrection, practiceMode) VALUES (?, ?, ?, ?, ?, ?, ?);";
	public final static String SQL_GET_QUIZ = "SELECT * FROM " + QUIZ_TABLE + " WHERE quizID =?;";
	public final static String SQL_GET_QUIZ_ID = "SELECT LAST_INSERT_ID();";
	public final static String SQL_GET_RECENTLY_CREATED_QUIZZES = "SELECT quizId FROM " + QUIZ_TABLE + " ORDER BY quiz_timestamp DESC;";
	public final static String SQL_GET_AUTHOR_HISTORY = "SELECT quizId FROM " + QUIZ_TABLE + " WHERE author=? ORDER BY quiz_timestamp DESC;";
	
	//Messages
	private final static String MESSAGE_TABLE = "messages";
	public final static String SQL_ADD_MESSAGE = "INSERT INTO " +  MESSAGE_TABLE + " (userTo, userFrom, messageType, messageContent) VALUES (?, ?, ?, ?);";
	public final static String SQL_GET_MESSAGES = "SELECT * FROM " + MESSAGE_TABLE + " WHERE userTo=? ORDER BY messageTimeStamp;";
	public final static String SQL_GET_MESSAGE_ID = "SELECT LAST_INSERT_ID();";
	public final static String SQL_DELETE_MESSAGE = "DELETE FROM " + MESSAGE_TABLE + " WHERE messageId =?;";
	
	//Answers
	private final static String ANSWER_TABLE = "answers";
	public final static String SQL_ADD_ANSWER = "INSERT INTO " + ANSWER_TABLE + " VALUES(?, ?, ?);";
	public final static String SQL_GET_CORRECT_ANSWER = "SELECT answer FROM " + ANSWER_TABLE + " WHERE questionId=? AND correct = 1;";
	public final static String SQL_ALL_ANSWERS = "SELECT answer FROM " + ANSWER_TABLE + " WHERE questionId=?;";
	public final static String SQL_DELETE_ANSWERS = "DELETE FROM " + ANSWER_TABLE + " WHERE questionId =?;";
	
	//Questions
	private final static String QUESTION_TABLE = "questions";
	public final static String SQL_ADD_QUESTION = "INSERT INTO " + QUESTION_TABLE + " (quizID, questionContent, questioncontent2, weight, questionType, caseOrRandomize) VALUES(?, ?, ?, ?, ?, ?);";
	public final static String SQL_GET_QUESTION_ID = "SELECT LAST_INSERT_ID();";
	public final static String SQL_GET_QUESTION = "Select * FROM " + QUESTION_TABLE + " WHERE quizId=? ORDER BY questionId;";
	public final static String SQL_DELETE_QUESTIONS = "DELETE FROM " + QUESTION_TABLE + " WHERE quizId =?;";
	
	//Scores
	private final static String SCORES_TABLE = "scores";
	public final static String SQL_GET_SCORES = "SELECT quizId, score FROM " + SCORES_TABLE + " WHERE username=? ORDER BY scoreTimeStamp;";
	public final static String SQL_GET_TOP_TEN = "SELECT username, score FROM " + SCORES_TABLE + " WHERE quizId=? ORDER BY score LIMIT 10";
	public final static String SQL_ADD_SCORE = "INSERT INTO " + SCORES_TABLE + " (username, quizId, score) VALUES (?, ?, ?);";
	public final static String SQL_REMOVE_USER_HISTORY = "DELETE FROM " + SCORES_TABLE + " where username=?;";
	public final static String SQL_REMOVE_USER_HISTORY_ON_QUIZ = "DELETE FROM " + SCORES_TABLE + " where username=? AND quizId=?;";
	public final static String SQL_MOST_PLAYED_QUIZ = "SELECT quizId FROM (SELECT quizId, count(*) as c FROM " +  SCORES_TABLE + " GROUP BY quizId ORDER BY c DESC) as temp_table;";
	public final static String SQL_GET_USER_QUIZ_HISTORY = "SELECT score FROM " + SCORES_TABLE + " where username=? AND quizId=?;";
	
	
	//Some multi-table stuff
	public final static String SQL_DELETE_QUIZ = "DELETE FROM " + ANSWER_TABLE + " WHERE questionId IN (SELECT questionId FROM " + QUESTION_TABLE + " WHERE quizId=?);";
	public final static String SQL_DELETE_QUIZ_2 = "DELETE FROM " + QUESTION_TABLE + " WHERE quizId=?;";
	public final static String SQL_DELETE_QUIZ_3 = "DELETE FROM " + QUIZ_TABLE + " WHERE quizId=?;";
	public final static String SQL_GET_FRIEND_HISTORY = "SELECT quizId, f, score FROM " + SCORES_TABLE + " , (SELECT friend as f FROM friends WHERE user=?) as f_table WHERE scores.username = f_table.f ORDER BY scoreTimeStamp DESC;";
	
}