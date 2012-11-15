package vooga.platformer.leveleditor;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import vooga.platformer.gui.menu.GameButton;
import vooga.platformer.gui.menu.GameListener;

/**
 * Frame containing all the elements needed to build and save a level
 * 
 * @author Sam Rang
 *
 */
@SuppressWarnings("serial")
public class LevelEditor extends JFrame{
    private static final Dimension DEFAULT_FRAME_SIZE = new Dimension(640, 480);
    private static final String IMAGE_PATH = "src/vooga/platformer/data/";
    private Map<String, List<String>> mySpriteTypes;
    private JFrame myContainer;
    private JPanel myViewPane;
    private boolean myGameIsRunning;
    private LevelBoard myBoard;
    private KeyListener myKeyListener;
    private GameListener myGameListener;
    public static void main (String[] args) {
        new LevelEditor();
    }

    public LevelEditor() {
        super("LevelEditor");
        myGameIsRunning = true;
        frameBuild();
        fillMap();
        createListeners();
        createEditPane();
        createButtonPanel();
        createTopMenu();
        pack();
        setVisible(true);
        editLoop();
    }

    private void editLoop () {
        while (myGameIsRunning) {
            repaint();
        }
    }

    private void frameBuild() {
        myContainer = this;
        setPreferredSize(DEFAULT_FRAME_SIZE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void createListeners () {
        myKeyListener = new KeyAdapter() {

        };
        myGameListener = new GameListener() {
            @Override 
            public void actionPerformed(MouseEvent arg0) {
                createPopupMenu(arg0.getComponent(), arg0.getX(), arg0.getY());
            }
        };
    }
    private void createEditPane() {
        LevelBoard board = new LevelBoard(DEFAULT_FRAME_SIZE);
        myBoard = board;
        JPanel panel = new JPanel() {
            @Override 
            public void paint(Graphics g) {
                g.clearRect(0, 0, DEFAULT_FRAME_SIZE.width, DEFAULT_FRAME_SIZE.height);
                g.setColor(Color.GREEN);
                myBoard.paint(g);
                super.paintComponents(g);
            }
        };
        panel.setLayout(new BorderLayout());
        myViewPane = panel;
        panel.addMouseListener(myBoard.getMouseListener());
        panel.addKeyListener(myKeyListener);
        myContainer.add(panel);
    }
    private GameButton createButton (String spritename) {
        GameButton gb = new GameButton(spritename);
        gb.setGameListener(myGameListener);
        return gb;
    }
    private void createButtonPanel() {
        JPanel panel = new JPanel();
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new GridLayout(mySpriteTypes.size(), 1));
        subpanel.setPreferredSize(new Dimension(50, 150));
        for (String sprite : mySpriteTypes.keySet()) {
            subpanel.add(createButton(sprite));
        }
        subpanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(subpanel, BorderLayout.CENTER);
        panel.setOpaque(false);
        myViewPane.add(panel, BorderLayout.WEST);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        myBoard.update();
    }

    protected void createPopupMenu(final Component comp, final int x,
            final int y) {
        JPopupMenu pop = new JPopupMenu();
        for (String subsprite : mySpriteTypes.get(comp.getName())) {
            JMenuItem j = new JMenuItem(subsprite);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    Sprite s = new Sprite(event.getActionCommand(), x, y, 40, 40, 
                            IMAGE_PATH + event.getActionCommand() + ".png");
                    myBoard.add(s);
                }
            });
            pop.add(j);
        }
        pop.show(comp, x, y);
    }
    private void createTopMenu () {
        JMenuBar bar = new JMenuBar();
        JMenu levelMenu = new JMenu("Level");
        levelMenu.add(new AbstractAction("Load") {
            @Override
            public void actionPerformed (ActionEvent e) {
                load();
            }
        });
        levelMenu.add(new AbstractAction("Clear") {
            @Override
            public void actionPerformed (ActionEvent e) {
                clear();
            }
        });
        levelMenu.add(new AbstractAction("New") {
            @Override
            public void actionPerformed (ActionEvent e) {
                newLevel();
            }
        });
        JMenu spriteMenu = new JMenu("Sprite");
        spriteMenu.add(new AbstractAction("Load") {
            @Override
            public void actionPerformed (ActionEvent e) {
                load();
            }
        });
        spriteMenu.add(new AbstractAction("New") {
            @Override
            public void actionPerformed (ActionEvent e) {
                newLevel();
            }
        });
        bar.add(levelMenu);
        bar.add(spriteMenu);
        myViewPane.add(bar, BorderLayout.NORTH);
    }
    protected void newLevel () {
        System.out.println("New Level");
    }
    protected void clear () {
        System.out.println("Clear pallet");
    }
    protected void load () {
        System.out.println("Load existing level");
    }
    private void fillMap() {
        mySpriteTypes = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("Yoshi");
        list.add("Pink Yoshi"); 
        mySpriteTypes.put("Yoshi", list);
        list = new ArrayList<String>();
        list.add("Mario");
        mySpriteTypes.put("Mario", list);
        list = new ArrayList<String>();
        list.add("Bowser");
        list.add("Baby Bowser");
        mySpriteTypes.put("Bowser", list);
    }
}
