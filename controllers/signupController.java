package controllers;

// Class imports 
import main.QuizApp;
import main.ViewManager;
import scenes.LoginPage;
import sqlConnectors.DatabaseConnection;

// Java fx Imports 
import javafx.scene.Scene;
import scenes.SignupPage;
import javafx.stage.Stage;


public class signupController {
    private SignupPage signPage;

    public signupController() {  
        this.signPage = ViewManager.getSignupPage(); 
        setupEventListeners();
    }

    private void setupEventListeners() {
        signPage.getSignupButton().setOnAction(e -> signup());
    }

    private void signup() {
        System.out.println("Testing if signup button works");
        String username = signPage.getusername().getText();
        String password = signPage.getpassword().getText();

        boolean valid = DatabaseConnection.validateSignUP(username, password);

        if (valid) {
            System.out.println("You are signed up!");
            int userId = DatabaseConnection.getUserId(username, password);

            ViewManager.setUserId(userId);  
            ViewManager.goToQuizPage();  
        } else {
            System.out.println("Error signing up.");
        }
    }
}