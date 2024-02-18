package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PopUpMarket {

    private JDialog dialog;
    private JFrame owner;

    public PopUpMarket(JFrame owner) {
        this.owner = owner;
        dialog = new JDialog(owner, "Market", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(owner); // Center the dialog relative to the owner frame
        initializeUI();
    }

    private void initializeUI() {
        // Title label
        JLabel marketTitle = new JLabel("Market Options", SwingConstants.CENTER);
        marketTitle.setFont(new Font("Helvetica", Font.BOLD, 16));
        marketTitle.setForeground(Color.BLACK);
        dialog.add(marketTitle, BorderLayout.NORTH);

        // Buy button
        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> displayBuyOptions());

        // Setting up the main panel
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(buyButton);
        dialog.add(mainPanel, BorderLayout.CENTER);
    }

    private void displayBuyOptions() {
        // Clear existing content
        dialog.getContentPane().removeAll();

        // Buttons for buying options
        JButton fiftyFiftyButton = createButton("50/50", new Font("Helvetica", Font.PLAIN, 12), Color.BLACK, Color.LIGHT_GRAY, new Dimension(100, 30));
        JButton switchButton = createButton("Switch", new Font("Helvetica", Font.PLAIN, 12), Color.BLACK, Color.LIGHT_GRAY, new Dimension(100, 30));
        JButton hintButton = createButton("Hint", new Font("Helvetica", Font.PLAIN, 12), Color.BLACK, Color.LIGHT_GRAY, new Dimension(100, 30));
        JButton backButton = createButton("Back", new Font("Helvetica", Font.PLAIN, 12), Color.BLACK, Color.LIGHT_GRAY, new Dimension(100, 30));

        // Action for the "Back" button to return to the main market options
        backButton.addActionListener((ActionEvent e) -> {
            dialog.getContentPane().removeAll();
            initializeUI();
            dialog.revalidate();
            dialog.repaint();
        });

        // Set up panel for buy options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2, 5, 5)); // Grid layout for buttons
        optionsPanel.add(fiftyFiftyButton);
        optionsPanel.add(switchButton);
        optionsPanel.add(hintButton);
        optionsPanel.add(backButton);

        dialog.add(optionsPanel, BorderLayout.CENTER);

        // Refresh dialog to show new content
        dialog.revalidate();
        dialog.repaint();
    }

    private JButton createButton(String text, Font font, Color foreground, Color background, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setPreferredSize(size);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    public void display() {
        dialog.setVisible(true);
    }

    // Example main method to show usage
    public static void main(String[] args) {
        // Assuming you have a frame to pass as owner

    }
}
