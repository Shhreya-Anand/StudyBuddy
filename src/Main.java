import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.geometry.Insets;

import java.awt.font.*;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static JLabel userLabel; // To manage the username display
    private static JLabel scoreLabel = new JLabel("Score: 0"); // For displaying the dynamic score text
    private static JLabel userLabel2; // For displaying the score icon
    private static int score = 0; // Score variable
    private static String username = ""; // Username variable

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Advanced Game Board");
            frame.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Top panel with BorderLayout for specific alignment
            JPanel topPanel = new JPanel(new BorderLayout());

            // User label for the user icon and name (Batman)
            userLabel = createUserLabel("Images/superhero.png");
            userLabel.setText("Batman");
            topPanel.add(userLabel, BorderLayout.WEST); // Align Batman label on the left

            // Score section panel with BoxLayout for vertical alignment of score icon and
            // text
            JPanel scorePanel = new JPanel();
            scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
            userLabel2 = createUserLabel("Images/score.png"); // Score icon
            userLabel2.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the score icon
            scorePanel.add(userLabel2);

            scoreLabel.setText("Score: 0");
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the score text
            scorePanel.add(scoreLabel);

            // Wrap the scorePanel in another panel that uses FlowLayout for proper
            // alignment to the right
            JPanel rightAlignScorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            rightAlignScorePanel.add(scorePanel);

            topPanel.add(rightAlignScorePanel, BorderLayout.EAST); // Align score panel on the right

            Board board = new Board(10, 10); // Customize as needed

            // Bottom panel
            JPanel bottomPanel = new JPanel();
            // JTextArea textArea = new JTextArea(5, 20);
            // bottomPanel.add(new JScrollPane()); // Ensure text area is scrollable

            // Constraints for top panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 0.1; // 20% of vertical space
            frame.add(topPanel, gbc);

            // Constraints for board (center panel)
            gbc.gridy = 1;
            gbc.weighty = 0.8; // 60% of vertical space
            frame.add(board, gbc);

            // Constraints for bottom panel
            gbc.gridy = 2;
            gbc.weighty = 0.1; // 20% of vertical space
            frame.add(bottomPanel, gbc);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500); // Set the initial window size
            frame.setResizable(false); // Prevent resizing
            frame.setVisible(true);
        });
    }

    public static void setScore(int newScore) {
        score = newScore;
        scoreLabel.setText("Score: " + newScore);
        // No need to call repaint or revalidate explicitly for JLabel text updates in
        // this context
    }

    private static JLabel createUserLabel(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaledImage));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return label;
    }

    private Icon convertToMultiline(String text) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToMultiline'");
    }

    // Method to update the username
    public static void setUsername(String newUsername) {
        username = newUsername;
        userLabel.setText("Username: " + newUsername);
    }

    // Method to get the current username
    public static String getUsername() {
        return username;
    }

    // Method to update the score

    // Method to get the current score
    public static int getScore() {
        return score;
    }

}
