import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PopUpMarket {

    private JDialog dialog;
    private User user;
    private JFrame owner;

    public PopUpMarket(User user, JFrame owner) {
        this.user = user;
        this.owner = owner;
        dialog = new JDialog(owner, "Market", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(owner); // Center the dialog relative to the owner frame
        initializeUI();
    }

    private void initializeUI() {
        JLabel marketTitle = new JLabel("Market Options", SwingConstants.CENTER);
        marketTitle.setFont(new Font("Helvetica", Font.BOLD, 16));
        dialog.add(marketTitle, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5)); // Grid layout for buttons

        // Define buttons
        JButton fiftyFiftyButton = createButton("Buy 50/50 - 10 Coins");
        JButton switchButton = createButton("Buy Switch - 15 Coins");
        JButton skipButton = createButton("Buy Skip - 5 Coins");
        JButton backButton = createButton("Back");

        // Add action listeners
        fiftyFiftyButton.addActionListener(e -> buyItem("50/50", 10));
        switchButton.addActionListener(e -> buyItem("Switch", 15));
        skipButton.addActionListener(e -> buyItem("Skip", 5));
        backButton.addActionListener((ActionEvent e) -> dialog.dispose());

        // Add buttons to panel
        optionsPanel.add(fiftyFiftyButton);
        optionsPanel.add(switchButton);
        optionsPanel.add(skipButton);
        optionsPanel.add(backButton);

        dialog.add(optionsPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Helvetica", Font.PLAIN, 12));
        return button;
    }

    private void buyItem(String item, int cost) {
        if (user.getCoins() >= cost) {
            user.setCoins(user.getCoins() - cost);
            user.addItemToInventory(item, 1);
            JOptionPane.showMessageDialog(dialog, "You bought a " + item + "!");
        } else {
            JOptionPane.showMessageDialog(dialog, "Not enough coins to buy " + item + ".");
        }
    }

    public void display() {
        dialog.setVisible(true);
    }
}
