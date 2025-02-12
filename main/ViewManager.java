package main;

import controllers.LoginController;
import controllers.ProfileController;
import controllers.QuizController;
import controllers.signupController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.LoginPage;
import scenes.ProfilePage;
import scenes.QuizPage;
import scenes.SignupPage;

public class ViewManager {
    private static Stage primaryStage;  
    private static LoginPage loginView;
    private static QuizPage quizView;
    private static ProfilePage profileView;
    private static SignupPage signView;
    
    private static Scene quizScene, profileScene;  
    
    // Controlers 
    private static QuizController quizController;


    
    private static int userId;  

    public static void init(Stage stage) {
        primaryStage = stage;
        loginView = new LoginPage();  
        new LoginController();  
        primaryStage.setScene(new Scene(loginView.getRoot(), 400, 400));
        primaryStage.setTitle("Quiz Game");        
        primaryStage.show();
    }

    public static void setUserId(int id) {
        userId = id;  
    }
    
    public static void goToSignupPage() {
        if (signView == null) {
            signView = new SignupPage();
            new signupController();
        } else {
            new signupController();
        }
        primaryStage.setScene(new Scene(signView.getRoot(), 400, 400));
    }


    public static void goToQuizPage() {
        if (quizScene == null) {  // 
            quizView = new QuizPage();
            quizController = new QuizController();
            quizScene = new Scene(quizView.getRoot(), 600, 400);
        }
        primaryStage.setScene(quizScene);  
    }

    public static void goToProfilePage() {
        if (profileScene == null) {  // ✅ Only create once
            profileView = new ProfilePage();
            new ProfileController();
            profileScene = new Scene(profileView.getRoot(), 600, 400);
        }
        primaryStage.setScene(profileScene);  // ✅ Reuse scene
    }

    public static void goToLoginPage() {
        primaryStage.setScene(new Scene(loginView.getRoot(), 400, 400));  
    }
    
    // Getters 
    public static int getUserid() {
    	return userId;
    }
    public static LoginPage getLoginPage() {
    	return loginView;
    }
    public static ProfilePage getProfilePage() {
    	return profileView;
    }
    public static SignupPage getSignupPage() {  
        return signView;
    }
    public static QuizPage getQuizPage() {  
        return quizView;
    }
    public static QuizController getQuizController() {
        return quizController;
    }
    
    
    
}