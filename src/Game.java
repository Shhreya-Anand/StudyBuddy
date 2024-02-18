import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            User user = new User("Ria", "./data/hello-kitty-gif-pixel-sanrio-image-hello-kitty-frame.jpg", 30);
            Board board = new Board(10, 10, user); // Initialize Board with User
//            new MainFrame(board, user); // Initialize and display MainFrame with Board and User

            MainFrame mainFrame = new MainFrame(board, user);
            board.setMainFrame(mainFrame);

        });
    }
}
