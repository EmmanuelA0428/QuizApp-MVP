package main;

public class Question {
	private String question;
	private String answer;
	private int questionId;
	
	// Constructor 
	public Question(int questionId, String question, String answer) {
		this.questionId = questionId;
		this.question = question;
		this.answer = answer;
	}
	
	 // Getters
    public int getId() { return questionId; }
    public String getQuestion() { return question; }
    public String getCorrectAnswer() { return answer; }

    // Method to check if user's answer is correct
    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);  
    }
}
