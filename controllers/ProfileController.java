package controllers;

import main.ViewManager;
import scenes.ProfilePage;
import sqlConnectors.DatabaseConnection;

public class ProfileController {
	ProfilePage view;
	
	public ProfileController() {
        this.view = ViewManager.getProfilePage();  
        eventListeners();
	}
	
	public void eventListeners( ) {
		view.getBackButton().setOnAction(e -> { goBack(); });
		view.getResetButton().setOnAction(e -> {resetQuestion(); });
	}
	
	public void goBack() {
		ViewManager.goToQuizPage();
	}
	
	public void resetQuestion() {
		DatabaseConnection.resetQuestion(ViewManager.getUserid());
		QuizController quizController = ViewManager.getQuizController();
	    quizController.refreshQuestions(); 	   
	}

}
