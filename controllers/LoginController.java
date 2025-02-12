package controllers;

import main.ViewManager;
import scenes.LoginPage;
import sqlConnectors.DatabaseConnection;

public class LoginController {
	private LoginPage view;
	
	public LoginController() {
		this.view = ViewManager.getLoginPage();		
		
		// Call the event listener methods
		setupEventListeners();
		
	}
	
    private void setupEventListeners() {
		view.getLoginButton().setOnAction(e -> {login(); });
        view.getSignupButton().setOnAction(e -> goToSignUpPage());  
    }
	
	public void login() { // Loges the user in then the login button is clicked
			String username = view.getUsernameField().getText();
			String password = view.getPasswordField().getText();
			
			// Verify with SQL 
			boolean valid = DatabaseConnection.validateLogin(username, password);
			
			if (valid) { // If the user has as account 
				System.out.println("Loged in Succesfully");
				
				// Get user id 
				int userId = DatabaseConnection.getUserId(username, password);
				
				if (userId != -1) {
					System.out.println("User id" + userId);
					ViewManager.setUserId(userId);  // ✅ Store userId globally
                    ViewManager.goToQuizPage(); 
				} else {
					System.out.println("User Id not found");
				}
				
				
			} else {
				System.out.println("login Failed ");
			}
	}
	
	public void goToSignUpPage() { // Goes to the sign up page when button is clicked 
        ViewManager.goToSignupPage();  
	}
}
