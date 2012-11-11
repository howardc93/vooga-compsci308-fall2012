package vooga.platformer.gui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;


/**
 * @author Yaqi Zhang
 * 
 */
public class GameButton extends JComponent {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_BUTTON_WIDTH = 130;
    private static final int DEFAULT_BUTTON_HEIGHT = 40;
    private Image myImg;
    private String myImgName;
    private String myCommand = "";

    /**
     * Create a game button that allows to customize the image of the button.
     * 
     * @param fileName of the button image
     */
    public GameButton (String fileName) {
        setImage(fileName);
        setSize(new Dimension(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT));
        setAnimation();
    }

    /**
     * Create a game button that allows to customize the image of the button.
     * 
     * @param fileName of the button image
     * @param command String appears on the button.
     */
    public GameButton (String fileName, String command) {
        this(fileName);
        setString(command);
    }

    /**
     * Create a game button that allows to customize the image of the button.
     * 
     * @param fileName of the button image
     * @param command String appears on the button.
     * @param gl GameListener
     */
    public GameButton (String fileName, String command, MouseListener ml) {
        this(fileName);
        addMouseListener(ml);
    }

    /**
     * Set String appears on the button.
     * 
     * @param command String appears on the button
     */
    public void setString (String command) {
        myCommand = command;
    }

    @Override
    protected void paintComponent (Graphics pen) {
        Dimension myButtonSize = getSize();
        pen.drawImage(myImg, 0, 0, myButtonSize.width, myButtonSize.height,
                null);
        pen.setColor(Color.BLACK);
        pen.drawString(myCommand, (int) (getSize().width / 4), (int) (getSize()
                .getHeight() / 2));
    }

    /**
     * @param img image
     */
    public void setImage (Image img) {
        myImg = img;
    }

    /**
     * @param fileName in String
     */
    public void setImage (String fileName) {
        myImgName = fileName;
        setImage(fileName, "normal");
    }

    private void changeImage (String state) {
        setImage(myImgName, state);
    }

    /**
     * User define what will happen once this button being clicked in the
     * actionPerformed() of a GameListener.
     * 
     */
    public void setAnimation () {
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed (MouseEvent arg0) {
                changeImage("pressed");
            }

            @Override
            public void mouseReleased (MouseEvent arg0) {
                changeImage("normal");
            }
        };
        addMouseListener(ml);
    }

    /**
     * @param width
     * @param height
     */
    public void setButtonSize (int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    private void setImage (String fileName, String state) {
        try {
            myImg = ImageIO.read(new File(
                    "src/vooga/platformer/gui/menu/buttonimg/" + fileName + "."
                            + state + ".png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * @param size of the menu
     */
    public void setSize (Dimension size) {
        setPreferredSize(size);
    }
}
