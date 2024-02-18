import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> options;
    private String correctOption; // Letter indicating the correct option (e.g., "B")
    private Difficulty level; // Assuming Difficulty is an enum

    public QuizQuestion(String question, List<String> options, String correctOption, Difficulty level) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    // Returns the actual text of the correct option based on the letter
    public String getCorrectAnswerText() {
        int index = correctOption.charAt(0) - 'A'; // Convert letter to index (e.g., 'B' -> 1)
        return options.get(index);
    }

    public Difficulty getLevel() {
        return level;
    }
}
