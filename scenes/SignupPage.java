package scenes;

import javafx.scene.layout.*;
import javafx.scene.control.*;

public class SignupPage {
	
    private Label usernameLabel, passwordLabel; // Labels 
    private Button signupButton, backButton; // Buttons 
    private TextField usernameField, passwordField; // Input Fields 
    private VBox root; // Root
    
    public SignupPage() {  // Setting up UI
    	initializeUI();
       
    }
    
    private void initializeUI() {
    	 // Labels
        usernameLabel = new Label("Username");
        passwordLabel = new Label("Password");
        
        // Text Fields
        usernameField = new TextField();
        passwordField = new TextField();
        
        // Buttons
        signupButton = new Button("Sign Up");
        backButton = new Button("Back");
        
        // Layouts
        HBox usernameLayout = new HBox(10, usernameLabel, usernameField);
        HBox passwordLayout = new HBox(10, passwordLabel, passwordField);
        HBox buttons = new HBox(40, signupButton, backButton);
        
        // Root layout
        root = new VBox(20, usernameLayout, passwordLayout, buttons);
    }
    
    // ==================== Getters ====================
    
    public VBox getRoot() { return root; }
    public Button getSignupButton() { return signupButton; }
    public Button getBackButton() { return backButton; }
    public TextField getusername() {return usernameField;}
    public TextField getpassword() {return passwordField;}

}