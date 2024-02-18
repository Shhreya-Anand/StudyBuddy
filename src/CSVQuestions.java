import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVQuestions extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton;

    private String[] options;
    private String correctOption;

    private int score; // Variable to track the score

    public CSVQuestions() {
        setTitle("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsPanel.add(optionButtons[i]);
            optionGroup.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        score = 0; // Initialize the score
        loadNextQuestion(); // Load the first question
    }

    private void loadNextQuestion() {
        String csvFilePath = "questions.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Skip the header row
            reader.readLine();

            // Read the next question from the CSV file
            if ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                questionLabel.setText(parts[0]);
                options = new String[]{parts[1], parts[2], parts[3], parts[4]};
                correctOption = parts[5];

                for (int i = 0; i < 4; i++) {
                    optionButtons[i].setText(options[i]);
                    optionButtons[i].setSelected(false);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    if (options[i].equalsIgnoreCase(correctOption)) {
                        JOptionPane.showMessageDialog(this, "Correct!");
                        score += 10; // Increase the score by 10 for correct answer
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect! The correct answer is: " + correctOption);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Your score is: " + score); // Show the score
            loadNextQuestion(); // Load the next question after submitting
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CSVQuestions::new);
    }
}
