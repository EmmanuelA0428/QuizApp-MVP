package scenes;

// JavaFX imports 
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Custom imports 
import sqlConnectors.DatabaseConnection;
import main.Question;
import main.ViewManager;

public class QuizPage {
	
	private static Label questionLabel, score;
	private Label usernameLabel, resultLabel;
	private TextField answerField;
	private Button submitButton, nextButton, goToProfile;
	private VBox root;
	
	private int userId;
	private Stage stage;
	private String username;  
	private int userScore;    

	/** Constructor */
	public QuizPage() {
		this.userId = ViewManager.getUserid();
		fetchUserDetails(); 
		initializeUI();
	}
	
	/** Fetches and stores the user's username and score */
	private void fetchUserDetails() {
		this.username = DatabaseConnection.getUsername(userId);  
		this.userScore = DatabaseConnection.getScore(userId);   
	}

	private void initializeUI() { // Sets up all UI components.
		// Labels
		usernameLabel = new Label("User: " + username);
		score = new Label("Score: " + userScore);
		questionLabel = new Label("Waiting for question...");
		resultLabel = new Label();

		// Input & Buttons
		answerField = new TextField();
		submitButton = new Button("Submit");
		nextButton = new Button("Next");
		goToProfile = new Button("Profile");

		// Layouts
		HBox profileRow = new HBox(10, goToProfile, usernameLabel, score);
		HBox answerRow = new HBox(10, answerField, submitButton);

		// Root
		root = new VBox(10, profileRow, questionLabel, answerRow, resultLabel, nextButton);
	}
	
	/** Displays a new question */
	public static void displayQuestion(Question question) {
        if (question != null) {
            questionLabel.setText("Q: " + question.getQuestion());
        } else {
            questionLabel.setText("No questions left!");
        }
    }
	
	/** Updates the user's score */
	public static void updateScore(int newScore) {
		score.setText("Score: " + newScore);
	}
	
	// ========== Getters ==========
	public VBox getRoot() { return root; }
	public Label getQuestionLabel() { return questionLabel; }
	public TextField getAswer() { return answerField; }
	public Label getResultLabel() { return resultLabel; }
	public Stage getStage() { return stage; }

	public Button getSubmitButton() { return submitButton; }
	public Button getNextButton() { return nextButton; }
	public Button getGoToProfileButton() { return goToProfile; }
}