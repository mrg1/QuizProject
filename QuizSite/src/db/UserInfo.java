package db;

import user.*;
import java.sql.*;
import java.util.*;

import message.*;
public class UserInfo {
	private static Connection con;
	
	public static void getUser(String username){
		con = QuizDB.getConnection();
	}
	
	public static void addUser(String username, String password, String salt, boolean admin){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_USER);
			addStatement.setString(1, username);
			addStatement.setString(2, password);
			addStatement.setString(3, salt);
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
	
	public static ArrayList<Integer> getAchievments(String user){
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
	
	public static int addQuiz(String author, boolean rq, boolean mp, boolean ic, boolean pm){
		con = QuizDB.getConnection();
		int quizId = -1;
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_QUIZ);
			addStatement.setString(1, author);
			addStatement.setBoolean(2, rq);
			addStatement.setBoolean(3, mp);
			addStatement.setBoolean(4, ic);
			addStatement.setBoolean(5, pm);
			addStatement.execute();
			
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_QUIZ_ID);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				quizId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				String author = rs.getString(2);
				boolean rq = rs.getBoolean(3);
				boolean mp = rs.getBoolean(4);
				boolean ic = rs.getBoolean(5);
				boolean pm = rs.getBoolean(6);
				result = new Quiz(author, rq, mp, ic, pm);
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
	
	public static void deleteMessages(Message m){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_DELETE_MESSAGE);
			addStatement.setInt(1, m.getMessageId());
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}