package db;

public class QuizSqlStatements {
	private final static String USER_TABLE = "users";


	public final static String SQL_GET_USER = "SELECT username, password FROM " + USER_TABLE + " WHERE username=?;";
	public final static String SQL_GET_USERS = "SELECT username, password FROM " + USER_TABLE + " ORDER BY username;";
	public final static String SQL_DELETE_USER = "DELETE FROM " + USER_TABLE + " WHERE username=?;";
	public final static String SQL_ADD_USER = "INSERT INTO " + USER_TABLE + " (username, password, salt, admin) VALUES (?, ?, ?, ?);";
	public static final String SQL_GET_ADMIN = "SELECT username, password FROM " + USER_TABLE + " WHERE username=? AND admin=1;";
	public static final String SQL_CHANGE_USER_ADMIN = "UPDATE " + USER_TABLE + " SET admin=? WHERE username=?;";
	public static final String SQL_USER_GET_SALT = "SELECT salt FROM " + USER_TABLE + " WHERE username=?;";
	public static final String SQL_GET_PROFILE_PICTURE = "SELECT profile_picture FROM " + USER_TABLE + " WHERE username=?;";
	public static final String SQL_SET_PROFILE_PICTURE = "UPDATE " + USER_TABLE + " SET profile_picture=? WHERE username=?;";
	//Friend shit
	private final static String FRIENDS_TABLE = "friends";
	public final static String SQL_GET_FRIENDS = "SELECT friend FROM " + FRIENDS_TABLE + " WHERE " + "user=?;";
	public final static String SQL_ADD_FRIEND = "INSERT INTO " + FRIENDS_TABLE + " VALUES (?, ?);";
	public final static String SQL_DELETE_FRIEND = "DELETE FROM " + FRIENDS_TABLE + " WHERE (user=? AND friend=?) OR (user=? AND friend=?);";

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
	public final static String SQL_GET_QUIZZES_BY_TITLE = "SELECT quizId FROM " + QUIZ_TABLE + " ORDER BY name;";
	public static final String SQL_GET_QUIZ_DATE = "SELECT quiz_timestamp FROM " + QUIZ_TABLE + " WHERE quizId=?;" ;
	public static final String SQL_GET_QUIZ_NAME = "SELECT name FROM " + QUIZ_TABLE + " WHERE quizId=?;";;
	
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
	public final static String SQL_GET_QUESTION = "SELECT * FROM " + QUESTION_TABLE + " WHERE quizId=? ORDER BY questionId;";
	public final static String SQL_DELETE_QUESTIONS = "DELETE FROM " + QUESTION_TABLE + " WHERE quizId =?;";
	public final static String SQL_GET_ONE_QUESTION = "SELECT * FROM " + QUESTION_TABLE + " WHERE questionId=?;";
	
	//Scores
	private final static String SCORES_TABLE = "scores";
	public final static String SQL_GET_SCORES = "SELECT quizId, score, elapsed, scoreId FROM " + SCORES_TABLE + " WHERE username=? ORDER BY scoreTimeStamp DESC;";
	public final static String SQL_GET_TOP_TEN = "SELECT username, score, elapsed, scoreId FROM " + SCORES_TABLE + " WHERE quizId=? ORDER BY score DESC, elapsed ASC, scoreTimeStamp ASC LIMIT 10;";
	public final static String SQL_ADD_SCORE = "INSERT INTO " + SCORES_TABLE + " (username, quizId, score, elapsed) VALUES (?, ?, ?, ?);";
	public final static String SQL_REMOVE_USER_HISTORY = "DELETE FROM " + SCORES_TABLE + " where username=?;";
	public static final String SQL_REMOVE_QUIZ_HISTORY = "DELETE FROM " + SCORES_TABLE + " where quizId=?;";
	public final static String SQL_REMOVE_USER_HISTORY_ON_QUIZ = "DELETE FROM " + SCORES_TABLE + " where username=? AND quizId=?;";
	public final static String SQL_MOST_PLAYED_QUIZ = "SELECT quizId FROM (SELECT quizId, count(*) as c FROM " +  SCORES_TABLE + " GROUP BY quizId ORDER BY c DESC) as temp_table;";
	public final static String SQL_GET_USER_QUIZ_HISTORY = "SELECT score, elapsed, scoreId FROM " + SCORES_TABLE + " where username=? AND quizId=? ORDER BY scoreTimeStamp DESC;";
	public final static String SQL_GET_RECENT_QUIZ_ARREMPTS = "SELECT score, username, elapsed, scoreId FROM " + SCORES_TABLE + " WHERE quizId=? ORDER BY scoreTimeStamp DESC;";
	public final static String SQL_GET_DAYS_SCORES_ON_QUIZ = "SELECT score, username, elapsed, scoreId FROM " + SCORES_TABLE + " WHERE quizId=? AND scoreTimeStamp > DATE_SUB(NOW(), INTERVAL 1 DAY) ORDER BY score DESC, elapsed ASC, scoreTimeStamp ASC;";
	public static final String SQL_GET_SCORE_DATE = "SELECT scoreTimeStamp FROM " + SCORES_TABLE + " WHERE scoreId=?;";
	
	//Anouncements
	private final static String ANN_TABLE = "announcements";
	public final static String SQL_ADD_ANNOUNCEMENT = "INSERT INTO " + ANN_TABLE + " (user, content) VALUES(?, ?)";
	public final static String SQL_GET_ANNOUNCEMENTS = "Select user, content, annId FROM " + ANN_TABLE + " ORDER BY date DESC;";
	public final static String SQL_DELETE_ANNOUNCEMENT = "DELETE FROM " + ANN_TABLE + " WHERE annId=?;";

	//Some multi-table stuff
	public final static String SQL_DELETE_QUIZ = "DELETE FROM " + ANSWER_TABLE + " WHERE questionId IN (SELECT questionId FROM " + QUESTION_TABLE + " WHERE quizId=?);";
	public final static String SQL_DELETE_QUIZ_2 = "DELETE FROM " + QUESTION_TABLE + " WHERE quizId=?;";
	public final static String SQL_DELETE_QUIZ_3 = "DELETE FROM " + QUIZ_TABLE + " WHERE quizId=?;";
	public final static String SQL_GET_FRIEND_HISTORY = "SELECT quizId, f, score, elapsed, scoreId FROM " + SCORES_TABLE + " , (SELECT friend as f FROM friends WHERE user=?) as f_table WHERE scores.username = f_table.f ORDER BY scoreTimeStamp DESC;";

	//Tags and Categories
	private final static String TAG_TABLE = "tags";
	public final static String SQL_GET_TAGS_BY_CATEGORY = "SELECT quizId FROM " + TAG_TABLE + " WHERE tagType=?";
	public final static String SQL_GET_TAGS_BY_QUIZ = "SELECT tagType FROM " + TAG_TABLE + " WHERE quizId=?;";
	public final static String SQL_SET_TAG = "INSERT INTO " + TAG_TABLE + " (quizId, tagType) VALUES (?, ?);";
	public final static String SQL_GET_ALL_TAGS = "SELECT DISTINCT tagType FROM " + TAG_TABLE + ";";

	//Reporting
	private final static String REPORT_TABLE = "reports";
	public final static String SQL_ADD_REPORT = "INSERT INTO " + REPORT_TABLE + " (quizId) VALUES(?);";
	public final static String SQL_DELETE_REPORTS = "DELETE FROM " + REPORT_TABLE + " WHERE quizId=?;";
	public final static String SQL_GET_REPORTS = "SELECT quizId, count(*) as c FROM " + REPORT_TABLE + " GROUP BY quizId ORDER BY c DESC;";

	public static final String RATINGS_TABLE = "ratings";
	public static final String SQL_GET_RATINGS_BY_USER = "SELECT username, quizId, rating, review FROM " + RATINGS_TABLE + " WHERE username=? ORDER BY rating DESC, rating_time_stamp DESC";
	public static final String SQL_GET_RATINGS_FOR_QUIZ = "SELECT username, quizId, rating, review FROM " + RATINGS_TABLE + " WHERE quizId=? ORDER BY rating_time_stamp DESC";
	public static final String SQL_ADD_RATING = "INSERT INTO " + RATINGS_TABLE + " (username, quizId, rating, review) VALUES (?, ?, ?, ?);";


	public static final String SQL_DELETE_RATING_USER = "DELETE FROM " + RATINGS_TABLE + " WHERE username=?;";
	public static final String SQL_DELETE_RATING_QUIZ = "DELETE FROM " + RATINGS_TABLE + " WHERE quizId=?;";
	




	


	


	
	
	

}
