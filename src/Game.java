import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User user = new User("Ria", "./data/hello-kitty-gif-pixel-sanrio-image-hello-kitty-frame.jpg", 100);
            Board board = new Board(10, 10, user);
            // Create an instance of MainFrame
            MainFrame mainFrame = new MainFrame(board, user);
            mainFrame.setVisible(true);
            // Launch the quiz
//            CSVQuestions CSVQuestions = new CSVQuestions();
        });
    }
}