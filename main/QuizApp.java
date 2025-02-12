package main;

import javafx.application.*;
import javafx.stage.*;



public class QuizApp extends Application {
	
	public void start(Stage stage) {
		
        ViewManager.init(stage);  
	}
	
	// Main method to run Program 
	public static void main(String[] args) {
		launch(args);
	}

}
