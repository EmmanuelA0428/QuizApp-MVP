package controllers;

import main.Question;
import main.ViewManager;
import scenes.QuizPage;
import sqlConnectors.DatabaseConnection;

import java.util.List;

public class QuizController {
    private final QuizPage quizPage;
    private final int userId;
    private List<Question> questions;
    private int currentQuestionIndex;

    public QuizController() {
        this.userId = ViewManager.getUserid();
        this.quizPage = ViewManager.getQuizPage();
        this.questions = DatabaseConnection.getUnansweredQuestions(userId);
        this.currentQuestionIndex = 0;

        initializeQuiz();
    }

    /** Initializes the quiz: loads the first question and sets up button actions */
    private void initializeQuiz() {
        loadCurrentQuestion();
        setupButtonActions();
    }

    /** Sets up event listeners for quiz buttons */
    private void setupButtonActions() {
        quizPage.getSubmitButton().setOnAction(e -> validateAnswer());
        quizPage.getNextButton().setOnAction(e -> loadNextQuestion());
        quizPage.getGoToProfileButton().setOnAction(e -> ViewManager.goToProfilePage());
    }

    /** Loads and displays the current question */
    private void loadCurrentQuestion() {
        if (!questions.isEmpty() && currentQuestionIndex < questions.size()) {
            Question firstQuestion = questions.get(currentQuestionIndex);
            QuizPage.displayQuestion(firstQuestion);
        } else {
            quizPage.getResultLabel().setText("No unanswered questions available.");
        }
    }

    /** Loads the next question or completes the quiz */
    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            Question nextQuestion = questions.get(currentQuestionIndex);
            QuizPage.displayQuestion(nextQuestion);

            quizPage.getSubmitButton().setDisable(false);
        } else {
            completeQuiz();
        }
    }

    /** Handles the answer validation process */
    private void validateAnswer() {
        String userAnswer = quizPage.getAswer().getText().trim();

        // Ensure there is a question to validate
        if (questions.isEmpty() || currentQuestionIndex >= questions.size()) {
            quizPage.getResultLabel().setText("⚠ No question available to validate.");
            return;
        }

        Question question = questions.get(currentQuestionIndex);
        String correctAnswer = question.getCorrectAnswer();

        boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);
        quizPage.getResultLabel().setText(isCorrect ? "✅ Correct!" : "❌ Wrong! The correct answer is: " + correctAnswer);
        
        // Update database with user response
        DatabaseConnection.updateData(question, userId, isCorrect);

        // Update UI: Score and Reset Input Field
        QuizPage.updateScore(DatabaseConnection.getScore(userId));
        quizPage.getAswer().clear();  
        quizPage.getSubmitButton().setDisable(true);
    }

    /** Resets the quiz state, loads fresh questions, and clears results */
    public void refreshQuestions() {
        this.questions = DatabaseConnection.getUnansweredQuestions(userId);
        this.currentQuestionIndex = 0;
        loadCurrentQuestion();
        
        quizPage.getResultLabel().setText("");
        QuizPage.updateScore(0);
        quizPage.getSubmitButton().setDisable(false);
    }

    /** Handles quiz completion */
    private void completeQuiz() {
        quizPage.getResultLabel().setText("🎉 Quiz Completed! No more questions.");
        quizPage.getNextButton().setDisable(true);
        quizPage.getSubmitButton().setDisable(true);
    }
}