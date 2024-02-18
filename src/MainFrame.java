import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainFrame extends JFrame {
    private JTextArea bottomBarTextArea; // Changed from JLabel to JTextArea
    private List<String> notes = new ArrayList<>();

    public MainFrame(Board boardPanel, User user) {
        setTitle("Study Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadNotes(); // Load notes from file

        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.add(Box.createHorizontalGlue());

        // Initialize JTextArea for the bottom bar with text wrapping
        bottomBarTextArea = new JTextArea(2, 20); // Set initial rows and columns
        bottomBarTextArea.setEditable(false); // Make it non-editable
        bottomBarTextArea.setLineWrap(true); // Enable text wrapping
        bottomBarTextArea.setWrapStyleWord(true); // Wrap by words
        bottomBarTextArea.setBackground(topBar.getBackground()); // Match the background with the top bar
        bottomBarTextArea.setBorder(BorderFactory.createEmptyBorder()); // Remove border to make it look like a label
        bottomBarTextArea.setFont(new Font("SansSerif", Font.PLAIN, 12)); // Set font to match your UI

        // You may need to adjust the layout to better accommodate the JTextArea
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomBar.add(bottomBarTextArea);

        add(topBar, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        updateBottomBar(); // Initial update
    }



    private void loadNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/notes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load notes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateBottomBar() {
        if (notes.isEmpty()) return; // Guard against empty notes list

        String note = notes.get(new Random().nextInt(notes.size()));
        bottomBarTextArea.setText(note);
    }
}
