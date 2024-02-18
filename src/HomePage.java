
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class HomePage {

    private static JButton createButton(String text, Font font, Color foreground, Color background, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foreground);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(size);
        button.setOpaque(true);
        button.setBackground(background);
        button.setBorderPainted(false);
        return button;
    }



    public static void renderHome() {
        Gui login = new Gui(500, 700);
        JFrame frame = login.getFrame();
        frame.setTitle("Home Page");
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);
    }

}
