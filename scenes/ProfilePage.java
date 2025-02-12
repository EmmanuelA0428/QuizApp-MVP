package scenes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;
import sqlConnectors.DatabaseConnection;

public class ProfilePage {
	Button resetButton;
	Button backButton;
	Label useridLabel;
	Label usernameLabel;
	VBox root;
	
	public ProfilePage() {
		
		resetButton = new Button("Reset");	
		backButton = new Button("Back");
		
		useridLabel = new Label();
		usernameLabel = new Label();
		
		HBox profileRow = new HBox(10, usernameLabel, useridLabel);
		HBox buttonRow = new HBox(10, backButton, resetButton);
		
		root = new VBox(10, profileRow, buttonRow);
		
		
	}
	
	// Getters 
	public Button getBackButton() {return backButton;}
	public Button getResetButton() {return resetButton;}
	
	public Label getUseridLabel() {return useridLabel;}
	public Label getUsernameLabel() {return usernameLabel;}
	
	public VBox getRoot() {return root;}


   
}