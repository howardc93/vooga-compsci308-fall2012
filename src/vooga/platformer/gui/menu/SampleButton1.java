package vooga.platformer.gui.menu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * This class shows how to create a new GameButton.
 * @author Yaqi Zhang
 *
 */
public class SampleButton1 {
    public static void main (String[] args) {
        JFrame frame = new JFrame();
        GameButton gb = new GameButton("ball","Press");
        gb.setSize(new Dimension(200, 200));
        frame.getContentPane().add(gb);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}