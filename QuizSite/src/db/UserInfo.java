package db;

import user.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import question.*;
import quiz.Quiz;
import quiz.Score;

import message.*;
public class UserInfo {
	private static Connection con;

	public static User getUser(String username){
		con = QuizDB.getConnection();
		User result = null;
		try {
			PreparedStatement getStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_USER);
			getStatement.setString(1, username);
			ResultSet rs = getStatement.executeQuery();
			if(rs.next()){
				result = new User(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<User> getUsers(){
		con = QuizDB.getConnection();
		List<User> result = new ArrayList<User>();
		try {
			PreparedStatement getStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_USERS);
			ResultSet rs = getStatement.executeQuery();
			while(rs.next()){
				result.add(new User(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void addUser(String username, String password, int salt, boolean admin){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_USER);
			addStatement.setString(1, username);
			addStatement.setString(2, password);
			addStatement.setInt(3, salt);
			addStatement.setBoolean(4, admin);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteUser(String user){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_USER);
			addStatement.setString(1, user);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean userExists(String username){
		boolean result = false;
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_USER);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkPassword(String username, String password){
		boolean result = false;
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_USER);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				String actual = rs.getString(2); //In this instance password will be the only column in the resultset
				if(actual.equals(password)){
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void addFriend(String user, String friend) {
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_FRIEND);
			addStatement.setString(1, user);
			addStatement.setString(2, friend);
			addStatement.execute();
			//Got to add other way too
			addStatement.setString(1, friend);
			addStatement.setString(2, user);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getFriends(String user) {
		ArrayList<String> friends = new ArrayList<String>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_FRIENDS);
			selectStatement.setString(1, user);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				friends.add(rs.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}

	public static void addAchievment(String user, int ach_id){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_ACH);
			addStatement.setString(1, user);
			addStatement.setInt(2, ach_id);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Integer> getAchievements(String user){
		ArrayList<Integer> achs = new ArrayList<Integer>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_ACHS);
			selectStatement.setString(1, user);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				achs.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achs;
	}

	public static int addQuiz(Quiz q){
		con = QuizDB.getConnection();
		int quizId = -1;
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_QUIZ);
			addStatement.setString(1, q.getName());
			addStatement.setString(2, q.getAuthor());
			addStatement.setString(3, q.getDescription());
			addStatement.setBoolean(4, q.getRandom());
			addStatement.setBoolean(5, q.isOnePage());
			addStatement.setBoolean(6, q.immediateCorrection());
			addStatement.setBoolean(7, q.canPractice());
			addStatement.execute();
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUIZ_ID);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				quizId = rs.getInt(1);
				for(Question quest : q.getQuestions()){
					addQuestion(quizId, quest);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		q.setQuizId(quizId);
		return quizId;
	}

	public static Quiz getQuiz(int quizId){
		Quiz result = null;
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUIZ);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				Question[] questions = getQuestions(quizId);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String description = rs.getString(4);
				boolean rq = rs.getBoolean(5);
				boolean mp = rs.getBoolean(6);
				boolean ic = rs.getBoolean(7);
				boolean pm = rs.getBoolean(8);
				result = new Quiz(name, author, description, questions, rq, mp, ic, pm, quizId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static void deleteQuiz(int quizId){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_QUIZ);
			addStatement.setInt(1, quizId);
			addStatement.execute();
			addStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_QUIZ_2);
			addStatement.setInt(1, quizId);
			addStatement.execute();
			addStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_QUIZ_3);
			addStatement.setInt(1, quizId);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Question[] getQuestions(int quizId) {
		List<Question> result = new ArrayList<Question>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUESTION);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				Question question;
				int questionId = rs.getInt(1);
				String questionContent = rs.getString(3);
				String questionContent2 = rs.getString(4);
				int weight = rs.getInt(5);
				int questionType = rs.getInt(6);
				boolean caseOrRandomize = rs.getBoolean(7);
				List<String> answers = getAnswers(questionId);
				String correctAnswer = getCorrectAnswer(questionId);
				String[] answerArray = answers.toArray(new String[answers.size()]);
				switch(questionType){
					case QuestionInfo.FILL_BLANK_ID: question = new FillBlankQuestion(questionContent, questionContent2, answerArray, caseOrRandomize, weight);
						question.setID(questionId);
						result.add(question);
						break;
					case QuestionInfo.MULTIPLE_CHOICE_ID: question = new MultipleChoiceQuestion(questionContent, answerArray, correctAnswer, caseOrRandomize, weight);
						question.setID(questionId);
						result.add(question);
						break;
					case QuestionInfo.PICTURE_QUESTION_ID: question = new PictureQuestion(questionContent, answerArray, caseOrRandomize, weight);
						question.setID(questionId);
						result.add(question);
						break;
					case QuestionInfo.RESPONSE_QUESTION_ID: question = new ResponseQuestion(questionContent, answerArray, caseOrRandomize, weight);
						question.setID(questionId);
						result.add(question);
						break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result.toArray(new Question[result.size()]);
	}

	private static String getCorrectAnswer(int questionId) {
		String result = "";
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_CORRECT_ANSWER);
			selectStatement.setInt(1, questionId);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static List<String> getAnswers(int questionId) {
		List<String> result = new ArrayList<String>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_ALL_ANSWERS);
			selectStatement.setInt(1, questionId);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void addMessage(Message m){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_MESSAGE);
			addStatement.setString(1, m.getTo());
			addStatement.setString(2, m.getFrom());
			addStatement.setInt(3, m.getType());
			addStatement.setString(4, m.getContent());
			addStatement.execute();
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_MESSAGE_ID);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				m.setMessageId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Message> getMessages(String toWhom){
		List<Message> result = new ArrayList<Message>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_MESSAGES);
			selectStatement.setString(1, toWhom);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				String from = rs.getString(3);
				int messageType = rs.getInt(4);
				String content = rs.getString(5);
				int messageId = rs.getInt(1);
				switch (messageType) {
					case MessageInfo.REQUEST_ID: Request r = new Request(toWhom, from, content);
							r.setMessageId(messageId);
							result.add(r);
							break;
					case MessageInfo.CHALLENGE_ID: Challenge c = new Challenge(toWhom, from, content);
							c.setMessageId(messageId);
							result.add(c);
							break;
					case MessageInfo.NOTE_ID: Note n = new Note(toWhom, from, content);
							n.setMessageId(messageId);
							result.add(n);
							break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void deleteMessages(int messageId){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_MESSAGE);
			addStatement.setInt(1, messageId);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addQuestion(int quizId, Question q){
		con = QuizDB.getConnection();
		boolean caseOrRandomize = false;
		String questionContent = "";
		String questionContent2 = null;
		int questionType = q.getQuestionType();
		//Consider taking out this switch and making changes to the question interface
		switch(questionType){
			case QuestionInfo.MULTIPLE_CHOICE_ID: MultipleChoiceQuestion mc = (MultipleChoiceQuestion) q;
				caseOrRandomize = mc.getRandom();
				questionContent = mc.getText();
				break;
			case QuestionInfo.PICTURE_QUESTION_ID: PictureQuestion pq = (PictureQuestion) q;
				caseOrRandomize = pq.getCaseSensitive();
				questionContent = pq.getPictureURL();
				break;
			case QuestionInfo.FILL_BLANK_ID: FillBlankQuestion fb = (FillBlankQuestion) q;
				caseOrRandomize = fb.getCaseSensitive();
				questionContent = fb.getPre();
				questionContent2 = fb.getPost();
				break;
			case QuestionInfo.RESPONSE_QUESTION_ID: ResponseQuestion rq = (ResponseQuestion) q;
				caseOrRandomize = rq.getCaseSensitive();
				questionContent = rq.getText();
				break;
		}

		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_QUESTION);
			addStatement.setInt(1, quizId);
			addStatement.setString(2, questionContent);
			addStatement.setString(3, questionContent2);
			addStatement.setInt(4, q.getWeight());
			addStatement.setInt(5, q.getQuestionType());
			addStatement.setBoolean(6, caseOrRandomize);
			addStatement.execute();
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUESTION_ID);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				int questionId = rs.getInt(1);
				q.setID(questionId);
				for(String answer: q.getCorrectAnswers()){
					addAnswer(questionId, answer, true);
				}
				for(String answer: q.getIncorrectAnswers()){
					addAnswer(questionId, answer, false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addAnswer(int questionId, String answerContent, boolean correct){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_ANSWER);
			addStatement.setInt(1, questionId);
			addStatement.setString(2, answerContent);
			addStatement.setBoolean(3, correct);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static List<Score> getTopTen(int quizId){
		List<Score> result = new ArrayList<Score>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_TOP_TEN);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				result.add(new Score(rs.getInt(2), quizId, rs.getString(1), rs.getInt(3), rs.getInt(4)));
			}
		}
	    catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<Score> getHistory(String username){
		List<Score> result = new ArrayList<Score>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_SCORES);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				result.add(new Score(rs.getInt(2), rs.getInt(1), username, rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void addScore(Score s){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_SCORE);
			addStatement.setString(1, s.getUsername());
			addStatement.setInt(2, s.getQuizId());
			addStatement.setInt(3, s.getScore());
			addStatement.setInt(4, s.getElapsed());
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteHistory(String username){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_REMOVE_USER_HISTORY);
			addStatement.setString(1, username);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void deleteHistoryForQuiz(int quizId){
		con = QuizDB.getConnection();
		try {
			PreparedStatement deleteStatement = con.prepareStatement(QuizSqlStatements.SQL_REMOVE_QUIZ_HISTORY);
			deleteStatement.setInt(1, quizId);
			deleteStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Integer> popularQuizIds(){
		List<Integer> popular = new ArrayList<Integer>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_MOST_PLAYED_QUIZ);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				popular.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return popular;
	}

	public static List<Integer> recentlyCreatedQuizIds(){
		List<Integer> recent = new ArrayList<Integer>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_RECENTLY_CREATED_QUIZZES);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				recent.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recent;
	}

	public static List<Integer> getAuthorHistory(String username){
		List<Integer> history = new ArrayList<Integer>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_AUTHOR_HISTORY);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				history.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history;
	}

	public static List<Score> getFriendHistory(String username){
		List<Score> history = new ArrayList<Score>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_FRIEND_HISTORY);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				history.add(new Score(rs.getInt(3), rs.getInt(1),rs.getString(2), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history;
	}

	public static List<Score> getUserHistoryOnQuiz(String username, int quizId){
		List<Score> history = new ArrayList<Score>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_USER_QUIZ_HISTORY);
			selectStatement.setString(1, username);
			selectStatement.setInt(2, quizId);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				history.add(new Score(rs.getInt(1), quizId, username, rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history;
	}

	public static void deleteUserHistoryOnQuiz(String username, int quizId){
		con = QuizDB.getConnection();
		try {
			PreparedStatement deleteStatement = con.prepareStatement(QuizSqlStatements.SQL_REMOVE_USER_HISTORY_ON_QUIZ);
			deleteStatement.setString(1, username);
			deleteStatement.setInt(2, quizId);
			deleteStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Returns a list of quizIds
	public static List<Integer> getQuizzesByTitle(){
		List<Integer> quizzes = new ArrayList<Integer>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUIZZES_BY_TITLE);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				quizzes.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
	
	public static String getDateForQuiz(int quizId){
		String result = "";
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUIZ_DATE);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				Timestamp t = rs.getTimestamp(1);
				result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getDateForScore(int scoreId){
		String result = "";
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_SCORE_DATE);
			selectStatement.setInt(1, scoreId);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				Timestamp t = rs.getTimestamp(1);
				result = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Score> getRecentQuizAttempts(int quizId){
		List<Score> history = new ArrayList<Score>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_RECENT_QUIZ_ARREMPTS);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				history.add(new Score(rs.getInt(1), quizId,rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history;
	}
	
	public static List<Score> getDaysQuizAttempts(int quizId){
		List<Score> history = new ArrayList<Score>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_DAYS_SCORES_ON_QUIZ);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				history.add(new Score(rs.getInt(1), quizId,rs.getString(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history;
	}
	
	public static void deleteAnnouncement(int announcementId){
		con = QuizDB.getConnection();
		try {
			PreparedStatement deleteStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_ANNOUNCEMENT);
			deleteStatement.setInt(1, announcementId);
			deleteStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addAnnouncement(String username, String content){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_ANNOUNCEMENT);
			addStatement.setString(1, username);
			addStatement.setString(2, content);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Announcement> getAnnouncments(){
		List<Announcement> announcements = new ArrayList<Announcement>();
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_ANNOUNCEMENTS);
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()){
				announcements.add(new Announcement(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return announcements;
	}
	
	public static boolean quizExists(int quizId){
		boolean result = false;
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUIZ);
			selectStatement.setInt(1, quizId);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean isAdmin(String username){
		boolean result = false;
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_ADMIN);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void changeAdmin(String username, boolean admin){
		con = QuizDB.getConnection();
		try {
			PreparedStatement updateStatement = con.prepareStatement(QuizSqlStatements.SQL_CHANGE_USER_ADMIN);
			updateStatement.setString(2, username);
			updateStatement.setBoolean(1, admin);
			updateStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFriend(String username, String friend){
		con = QuizDB.getConnection();
		try {
			PreparedStatement deleteStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_FRIEND);
			deleteStatement.setString(1, friend);
			deleteStatement.setString(2, username);
			deleteStatement.setString(3, username);
			deleteStatement.setString(4, friend);
			deleteStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getSalt(String username){
		int result = 0;
		con = QuizDB.getConnection();
		try {
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_USER_GET_SALT);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
