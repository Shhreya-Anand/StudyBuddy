import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Frame;

public class PopUpMarket {
    private JDialog dialog;

    public PopUpMarket(Frame owner) {
        dialog = new JDialog(owner, "Pop Up Market!", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 200);
        // Add more initialization code here if needed
    }

    public void display() {
        // Example question - replace with your actual quiz content
        JLabel questionLabel = new JLabel("market", JLabel.CENTER);

        questionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dialog.dispose(); // Close the dialog on click
            }
        });

        dialog.add(questionLabel, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true);
    }
}
