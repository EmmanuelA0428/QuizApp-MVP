package sqlConnectors;

import main.Question;
import java.util.ArrayList;
import java.util.List;
// Imports 
import java.sql.*;

public class DatabaseConnection {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/QuizApp";
	 private static final String USER = "root";
	 private static final String PASSWORD = "Mysql0428";
	 
	 private static Connection con;
	 
	 // Establish Connection 
	 public static Connection getConnection() {
		    if (con == null) {  // Only create a new connection if one doesn't exist
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection(URL, USER, PASSWORD);
		        } catch (Exception e) {
		            System.out.println("Database Connection Failed");
		            e.printStackTrace();
		        }
		    }
		    return con;
		}
	 
	 // Method to validate login 
	 public static boolean validateLogin(String username, String password) {
		 String query = ""
		 		+ "SELECT * "
		 		+ "FROM users "
		 		+ "WHERE username = ?"
		 		+ "AND password = ? ";
		 
		 try {
			 PreparedStatement statement = getConnection().prepareStatement(query);
			 statement.setString(1, username);
			 statement.setString(2, password);
			 
			 ResultSet result = statement.executeQuery();
			 
			 if (result.next()) {
				 return true;
			 } else {
				 return false;
			 }
			 
		 } catch (Exception e) {
			 System.out.println(e);
			 
			 return false;

		 }
	 }
		 
	 public static boolean validateSignUP(String username, String password) {
			 String query = ""
			 		+ "INSERT INTO users(username, password) "
			 		+ "values(?,?)";
			 
			 try {
				 PreparedStatement statement = getConnection().prepareStatement(query);
				 statement.setString(1, username);
				 statement.setString(2, password);
				 
				 int result = statement.executeUpdate();
				 
				 if (result == 1) {
					 return true;
				 } else {
					 return false;
							 
				 }
				 
			 } catch(Exception e) {
				 System.out.println(e);
				 return false;
			 }
		 
		 
	 }
	 
	 public static int getUserId(String username, String password) {
		 String query = ""
		 		+ "SELECT id "
		 		+ "FROM users "
		 		+ "WHERE username = ? "
		 		+ "AND password = ? ";
		 
		 try {
			 PreparedStatement statement = con.prepareStatement(query);
			 statement.setString(1, username);
			 statement.setString(2, password);
			 
			 ResultSet result = statement.executeQuery();

			 if (result.next()) {
				 int userId = result.getInt("id");
				 return userId; 
			 } else {
				 System.out.println("Result set is empty - The query return nothing");
				 return -1;
			 }

		 } catch(Exception e) {
		        System.out.println("Error fetching user ID: " + e.getMessage()); // 
			 return -1; // Invalid user Id 
		 }
		 
	 }
	 
	 public static String getUsername(int userid) {
		 String query = ""
		 		+ "SELECT username "
		 		+ "FROM Users "
		 		+ "WHERE id = ? ";
		 
		 try {
			 
			 PreparedStatement statement = con.prepareStatement(query);
			 statement.setInt(1, userid);
			 
			 ResultSet result = statement.executeQuery();
			 
			 if (result.next()) {
				 String username = result.getString("username");
				 return username;
			 } else {
				 System.out.println("Result set returned nothing - check query or user id");
				 return null;
			 }
			 
		 } catch (Exception e) {
			 System.out.println("Could not get user's name " + e.getLocalizedMessage());
			 e.printStackTrace();
			 return null;
		 }
	 }
	 
	 public static List<Question> getUnansweredQuestions(int userId) {
		 List <Question> questions = new ArrayList<>();
		 
		 String query = "SELECT * FROM questions " +
	               "WHERE id NOT IN (SELECT question_id FROM quiz_results WHERE user_id = ?)"
	               + "ORDER BY id";
		 
		 try {
			 
			 PreparedStatement statement = con.prepareStatement(query);
			 statement.setInt(1, userId);
			 
			 ResultSet result = statement.executeQuery();
			 
			 while (result.next()) {
				 int id = result.getInt("id");
				 String question = result.getString("question_text");
				 String answer = result.getString("correct_answer");

				 questions.add(new Question(id, question, answer));
			 }

			 
		 } catch (Exception e) {
			 System.out.println("Error fetching unanswered questions: " + e.getMessage());
	         e.printStackTrace();
		 }
		 return questions;
	 }
	 
	 public static void updateData(Question currentQuestion, int userId, boolean correct) {
		 Date today = new Date(System.currentTimeMillis());
		 int questionId = currentQuestion.getId();
		 
		 String query = "INSERT INTO quiz_results(user_id, question_id, result, date) "
		 		+ "values(?,?,?,?) ";
		 
		 try {
			 PreparedStatement statement = con.prepareStatement(query);
			 statement.setInt(1, userId);
			 statement.setInt(2, questionId);
			 statement.setBoolean(3, correct);
			 statement.setDate(4, today);
			 
			 statement.executeUpdate();			 

		 } catch (Exception e) {
			 System.out.println("Issue in update Data" + e);
			 e.printStackTrace();
		 }
	 }
	 
	 public static int getScore(int userId) {
		    String query = "SELECT COUNT(*) AS score FROM quiz_results WHERE user_id = ? AND result = true";
		    int score = 0;

		    try {
		        PreparedStatement statement = con.prepareStatement(query);
		        statement.setInt(1, userId);
		        ResultSet result = statement.executeQuery();

		        if (result.next()) {
		            score = result.getInt("score");  // ✅ Retrieve score from query result
		        }
		    } catch (SQLException e) {
		        System.out.println("Error fetching user score: " + e.getMessage());
		        e.printStackTrace();
		    }
		    return score;  
		}
	 
	 public static void resetQuestion(int userID) {
		 String query = "DELETE FROM quiz_results WHERE user_id = ? ";
		 
		 try {
			 PreparedStatement statement = con.prepareStatement(query);
			 statement.setInt(1, userID);
			 int rowsDeleted = statement.executeUpdate();
			 
			 if (rowsDeleted > 0) {
		           System.out.println("✅ Successfully reset quiz progress for user ID: " + userID);
		           
			 } else {
		            System.out.println("⚠ No quiz progress found for user ID: " + userID);
			 }
		 } catch(Exception e) {
			 System.out.println("❌ Error resetting quiz progress: " + e.getMessage());
		     e.printStackTrace();  		 
		 	}
	 }
	 
	 public static void resetScore() {
		 
	 }
	 
	 
}
