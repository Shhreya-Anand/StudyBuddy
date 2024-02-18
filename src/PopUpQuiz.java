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

    private static final int MAX_QUESTIONS = 5;
    private JFrame frame;
    private int currentQuestionIndex = 0;
    private List<QuizQuestion> questions = new ArrayList<>();
    private User user;
    private int userScore = 0;
    private JPanel optionsPanel; // Panel to hold question options
    private ButtonGroup group; // Group for radio buttons

    public PopUpQuiz(User user) { // Constructor now requires a User object
        this.user = user; // Set the user object
        frame = new JFrame("Quiz Time");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        generateQuestions(); // Generate an initial set of questions
        displayQuestion(); // Display the first question
    }


    private void displayQuestion() {
        if (currentQuestionIndex >= MAX_QUESTIONS) {
            displayFinalResults();
            return;
        }

        frame.getContentPane().removeAll();

        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(currentQuestion.getQuestion()));

        group = new ButtonGroup();
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        List<String> options = new ArrayList<>(currentQuestion.getOptions());
        Collections.shuffle(options); // Shuffle options to randomize their order

        for (String option : options) {
            JRadioButton button = new JRadioButton(option);
            button.setActionCommand(option);
            group.add(button);
            optionsPanel.add(button);
        }

        JButton submitButton = new JButton("Submit");
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

    // Function to randomly remove two incorrect options
    public void fiftyFifty() {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        List<String> optionsToRemove = new ArrayList<>();
        for (String option : currentQuestion.getOptions()) {
            if (!option.equals(currentQuestion.getCorrectOption()) && optionsToRemove.size() < 2) {
                optionsToRemove.add(option);
            }
        }

        Component[] components = optionsPanel.getComponents();
        for (Component component : components) {
            JRadioButton button = (JRadioButton) component;
            if (optionsToRemove.contains(button.getText())) {
                optionsPanel.remove(button);
                group.remove(button);
            }
        }

        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    // Function to skip the current question
    public void skip() {
        currentQuestionIndex++;
        displayQuestion();
    }

    // Function to switch the current question
    public void switchQ() {
        if (questions.size() > currentQuestionIndex + 1) {
            currentQuestionIndex++; // Move to the next question
        }
        displayQuestion();
    }

    private void generateQuestions() {
        String path = "./data/questions.csv";
        List<QuizQuestion> allQuestions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip header if present
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    String question = values[0];
                    List<String> options = Arrays.asList(values[1], values[2], values[3], values[4]);
                    String correctOption = values[5];
                    // Optionally handle level or other fields
                    // if user exp<30 then easy
                    // if user exp<60 then medium
                    // if user exp>60 then hard
                    QuizQuestion quizQuestion;
                    if (user.getExp() < 30) {
                         quizQuestion = new QuizQuestion(question, options, correctOption, Difficulty.Easy);
                    } else if (user.getExp() < 60) {
                         quizQuestion = new QuizQuestion(question, options, correctOption, Difficulty.Intermediate);
                    } else {
                         quizQuestion = new QuizQuestion(question, options, correctOption, Difficulty.Advanced);

                    }
                    allQuestions.add(quizQuestion);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(allQuestions);
        questions = allQuestions.subList(0, Math.min(allQuestions.size(), MAX_QUESTIONS));
    }

//    private Difficulty levelToDifficulty(String level) {
//        switch (level) {
//            case "easy":
//                return Difficulty.Easy;
//            case "medium":
//                return Difficulty.Intermediate;
//            case "hard":
//                return Difficulty.Advanced;
//            default:
//                return Difficulty.Intermediate; // Default to medium if level is unknown
//        }
//    }


    private void processAnswer(String selectedOption) {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

        if (currentQuestion.getCorrectOption().equals(selectedOption)) {
            userScore += 10; // Correct answer, +10 points
            user.setExp(user.getExp() + 10); // Update user experience
        } else {
            userScore -= 5; // Incorrect answer, -5 points
            user.setExp(user.getExp() - 5); // Deduct experience for wrong answer
        }

        currentQuestionIndex++;
        displayQuestion(); // Move to the next question or end quiz
    }

    private void displayFinalResults() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Quiz complete! Your score: " + userScore));
//        panel.add(new JLabel("Earned points: " + userScore));
        panel.add(new JLabel("Current user experience: " + user.getExp()));

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    public void fiftFifty(){
        // delete two options

    }

    public void display() {
        frame.setVisible(true);
    }
}
