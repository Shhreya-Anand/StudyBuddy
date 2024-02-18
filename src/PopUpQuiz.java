import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PopUpQuiz {

    private static final int MAX_QUESTIONS = 5; // Limit the number of questions
    private JFrame frame;
    private int currentQuestionIndex = 0;
    private List<QuizQuestion> questions = new ArrayList<>();
    private User user; // User who is taking the quiz
    private int userScore = 0; // User's score
    private JPanel optionsPanel; // Panel to hold question options
    private ButtonGroup group; // Group for radio buttons

    public PopUpQuiz(User user) {


        this.user = user;
        frame = new JFrame("Quiz Time");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600); // Adjusted to match CSVQuestions size
        generateQuestions();
        displayQuestion();

    }

    private void displayQuestion() {
        if (currentQuestionIndex >= MAX_QUESTIONS) {
            displayFinalResults(); // Display final results if all questions have been answered
            return;
        }

        frame.getContentPane().removeAll(); // Clear the frame for the next question

        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(currentQuestion.getQuestion())); // Display the question

        group = new ButtonGroup();
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        List<String> options = new ArrayList<>(currentQuestion.getOptions());
        Collections.shuffle(options); // Shuffle options for randomness

        for (String option : options) {
            JRadioButton button = new JRadioButton(option);
            button.setActionCommand(option);
            group.add(button);
            optionsPanel.add(button);
        }

        JButton submitButton = new JButton("Submit");
        // cent
        submitButton.addActionListener(e -> {
            if (group.getSelection() != null) {
                String selectedOption = group.getSelection().getActionCommand();
                processAnswer(selectedOption);
            }
        });

        panel.add(optionsPanel);
        panel.add(submitButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    private void processAnswer(String selectedOption) {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.getCorrectOption().equals(selectedOption)) {
            userScore += 10; // Increment score for correct answer
            user.setExp(user.getExp() + 10); // Increase user experience
        } else {
                JOptionPane.showMessageDialog(frame, "Incorrect! The correct answer is: " + currentQuestion.getCorrectOption());
            userScore -= 5; // Decrement score for wrong answer
            user.setExp(user.getExp() - 5); // Decrease user experience
        }
        currentQuestionIndex++;
        displayQuestion(); // Display the next question
    }

    private void displayFinalResults() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Quiz complete! Your score: " + userScore));
        panel.add(new JLabel("Current user experience: " + user.getExp()));

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    private void generateQuestions() {
        String path = "./data/questions.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    String question = values[0];
                    List<String> options = Arrays.asList(values[1], values[2], values[3], values[4]);
                    String correctOption = values[5];
                    QuizQuestion quizQuestion;
                    if (user.getExp() < 30) {
                        quizQuestion = new QuizQuestion(question, options, correctOption, Difficulty.Easy);
                    } else if (user.getExp() < 60) {
                        quizQuestion = new QuizQuestion(question, options, correctOption, Difficulty.Intermediate);
                    } else {
                        quizQuestion = new QuizQuestion(question, options, correctOption, Difficulty.Advanced);

                    }
                    questions.add(quizQuestion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(questions); // Shuffle questions
        questions = questions.subList(0, Math.min(questions.size(), MAX_QUESTIONS)); // Limit questions
    }

    public void display() {
        frame.setVisible(true);
    }

    // Main method for demonstration
    public static void main(String[] args) {
        // Assume User is correctly implemented elsewhere
        User user = new User("Username", "AvatarPath", 0);
        SwingUtilities.invokeLater(() -> new PopUpQuiz(user));
    }
}
