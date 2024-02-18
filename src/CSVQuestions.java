import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVQuestions implements ActionListener {
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton submitButton;

    private String[] options;
    private String correctOption;

    private int score; // Variable to track the score
    private boolean visible;

    public CSVQuestions() {
        Gui gui = new Gui(400, 600); // Create a GUI with specified height and width
        frame = gui.getFrame();
        frame.setTitle("Quiz Game");

        questionLabel = new JLabel();
        questionLabel.setBounds(10, 10, 580, 50); // Set bounds for the label
        frame.add(questionLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null); // Use null layout for manual placement
        optionsPanel.setBounds(10, 70, 580, 200); // Set bounds for options panel

        optionGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setBounds(10, (i * 50), 560, 40); // Set bounds for each radio button
            optionsPanel.add(optionButtons[i]);
            optionGroup.add(optionButtons[i]);
        }
        frame.add(optionsPanel);

        submitButton = new JButton("Submit");
        submitButton.setBounds(250, 300, 100, 40); // Set bounds for submit button
        submitButton.addActionListener(this);
        frame.add(submitButton);

        frame.setVisible(true); // Make the frame visible

        score = 0; // Initialize the score
        loadNextQuestion(); // Load the first question
    }

    private void loadNextQuestion() {
        String csvFilePath = "./data/questions.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            // Skip the header row
            reader.readLine();

            // Read the next question from the CSV file
            if ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                questionLabel.setText("<html>" + parts[0] + "</html>"); // Use HTML to enable text wrapping
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
                        JOptionPane.showMessageDialog(frame, "Correct!");
                        score += 10; // Increase the score by 10 for correct answer
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Incorrect! The correct answer is: " + correctOption);
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "Your score is: " + score); // Show the score
            loadNextQuestion(); // Load the next question after submitting
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CSVQuestions());
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
