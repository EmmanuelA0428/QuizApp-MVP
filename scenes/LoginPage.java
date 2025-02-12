package scenes;

import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import controllers.LoginController;
import controllers.signupController;

/**
 * LoginPage represents the login screen UI in the application.
 */
public class LoginPage {
	
	private Label usernameLabel, passwordLabel; // Labels 
	private Button loginButton, signupButton; 	// Buttons 
	private TextField usernameField, passwordField; // Input fields 
	private VBox root;	// Root 

	
	public LoginPage() { // Constructor - Initializes the LoginPage UI components.
		
		initializeUI();
	}
		
    private void initializeUI() { // Sets up all UI components.
        // Labels
        usernameLabel = new Label("Username:");
        passwordLabel = new Label("Password:");

        // Input Fields
        usernameField = new TextField();
        passwordField = new TextField();

        // Buttons
        loginButton = new Button("Login");
        signupButton = new Button("Sign Up");

        // Layouts
        HBox usernameLayout = new HBox(10, usernameLabel, usernameField);
        HBox passwordLayout = new HBox(10, passwordLabel, passwordField);
        HBox buttonLayout = new HBox(40, loginButton, signupButton);

        // Root layout container
        root = new VBox(20, usernameLayout, passwordLayout, buttonLayout);
    
    }

	
    // ==================== Getters ====================
 
	public VBox getRoot() {return root;} 
	public Button getLoginButton() {return loginButton;}
	public Button getSignupButton() {return signupButton;}
	public TextField getUsernameField() {return usernameField;}
	public TextField getPasswordField() {return passwordField;}

}
