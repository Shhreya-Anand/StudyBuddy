import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> options;
    private String correctOption; // This is expected to be a letter ('A', 'B', 'C', 'D') that corresponds to the correct answer
    private Difficulty difficulty;

    public QuizQuestion(String question, List<String> options, String correctOption, Difficulty difficulty) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.difficulty = difficulty;
    }



    public boolean isCorrect(String selectedOption) {
        return this.correctOption.equalsIgnoreCase(selectedOption);
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
