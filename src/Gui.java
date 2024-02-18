
import javax.swing.*;

public class Gui {
    private JFrame frame;

    public Gui(int height, int width) {
        this.frame = new JFrame();
        this.frame.setSize(width, height);
        this.frame.setLayout(null);

        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

    }

    public JFrame getFrame() {
        return this.frame;
    }
}