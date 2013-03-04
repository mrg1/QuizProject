package db;

import java.sql.*;



public class UserInfo {
	private static Connection con;
	
	public static void getUser(String username){
		con = QuizDB.getConnection();
	}
	
	public static void addUser(String username, String password){
		con = QuizDB.getConnection();
		try {
			PreparedStatement addStatement = con.prepareStatement(QuizSqlStatements.SQL_ADD_USER);
			addStatement.setString(1, username);
			addStatement.setString(2, password);
			addStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		QuizDB.close();
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
			PreparedStatement selectStatement = con.prepareStatement(QuizSqlStatements.SQL_GET_PASSWORD);
			selectStatement.setString(1, username);
			ResultSet rs = selectStatement.executeQuery();
			if(rs.next()){
				String actual = rs.getString(1); //In this instance password will be the only column in the resultset
				if(actual.equals(password)){
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		QuizDB.close();
		return result;
	}
	
	
}

