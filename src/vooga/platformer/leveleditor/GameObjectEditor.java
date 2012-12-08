package vooga.platformer.leveleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import vooga.platformer.gameobject.GameObject;
import vooga.platformer.gameobject.UpdateStrategy;
import vooga.platformer.levelfileio.LevelFileIOException;


/**
 * A pop up menu that allows users to edit the characteristics of GameObjects.
 * 
 * @author Sam Rang
 * 
 */
@SuppressWarnings("serial")
public class GameObjectEditor extends JPopupMenu {
    private static final int DEFAULT_ICON_WIDTH = 40;
    private GameObject myObject;
    private List<Class> myStrategies;

    /**
     * Opens a new pop up, adds an empty border and adds the
     * info and editable fields
     * 
     * @param obj GameObject to be modified
     */
    public GameObjectEditor (final GameObject obj) {
        myObject = obj;
        parseStrategies();
        JPanel myTop = getInfoPanel();
        add(myTop);
        JPanel valuePanel = getValuePanel();
        add(valuePanel);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    }

    private void parseStrategies () {
        Scanner s = null;
        try {
            s = new Scanner(new File(System.getProperty("user.dir") +
                    "/src/vooga/platformer/data/AvailableStrategies.txt"));
        }
        catch (FileNotFoundException e) {
            throw new LevelFileIOException("Could not find the level in the file system", e);
        }
        while (s.hasNext()) {
            String item = s.nextLine();
            Class c = (Class) createObject(item);
            myStrategies = new ArrayList<Class>();
            myStrategies.add(c);
        }
    }

    /**
     * useful class http://www.java-forums.org/java-lang/7894-object-reflection-creating-new-instances.html
     * @param className
     * @return
     */
    private Object createObject(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * Gets info about the object that the user can't change 
     * and shows the default Image of the object
     * 
     * @return JPanel with info and image displayed
     */
    private JPanel getInfoPanel () {
        JPanel myTop = new JPanel();
        myTop.setLayout(new BorderLayout());
        JPanel topCenter = new JPanel();
        topCenter.setLayout(new FlowLayout());
        JLabel myImage = new JLabel();
        Image i = myObject.getCurrentImage();
        myImage.setIcon(new ImageIcon(i.getScaledInstance(DEFAULT_ICON_WIDTH,
                i.getHeight(null) * DEFAULT_ICON_WIDTH / i.getWidth(null), Image.SCALE_DEFAULT)));
        myImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topCenter.add(myImage);

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(2, 1, 5, 5));
        JLabel myID = new JLabel("ID:   " + ((Integer)myObject.getId()).toString());
        info.add(myID);
        JLabel type = new JLabel("GameObject Type: " + myObject.getClass().getSimpleName());
        info.add(type);
        topCenter.add(info);

        JLabel prompt = new JLabel("Please enter Doubles:");
        myTop.add(topCenter, BorderLayout.CENTER);
        myTop.add(prompt, BorderLayout.SOUTH);
        return myTop;
    }

    /**
     * Creates a JPanel with editable fields that display the current
     * value and can be overwritten 
     * 
     * @return JPanel with labels and text fields
     */
    private JPanel getValuePanel () {
        JPanel myBottom = new JPanel();
        myBottom.setLayout(new GridLayout(6, 2));
        JLabel label;
        JTextField textField;
        final List<JTextField> fields = new ArrayList<JTextField>();
        // X
        label = new JLabel("X position");
        textField = new HintTextField(((Double) myObject.getX()).toString());
        fields.add(textField);
        myBottom.add(label);
        myBottom.add(textField);

        // Y
        label = new JLabel("Y position");
        textField = new HintTextField(((Double) myObject.getY()).toString());
        fields.add(textField);
        myBottom.add(label);
        myBottom.add(textField);

        // Width
        label = new JLabel("Width");
        textField = new HintTextField(((Double) myObject.getWidth()).toString());
        fields.add(textField);
        myBottom.add(label);
        myBottom.add(textField);

        // Height
        label = new JLabel("Height");
        textField = new HintTextField(((Double) myObject.getHeight()).toString());
        fields.add(textField);
        myBottom.add(label);
        myBottom.add(textField);

        // Image
        label = new JLabel("Image");
        JButton choose = new JButton("Choose Image");
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                FileNameExtensionFilter filter =
                        new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        myObject.setImage(ImageIO.read(chooser.getSelectedFile()));
                    }
                    catch (IOException e1) {
                        showError("file does not exist");
                        e1.printStackTrace();
                    }
                }
            }
        });
        myBottom.add(label);
        myBottom.add(choose);

        JButton accept = new JButton("Accept");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {
                    double x = Double.parseDouble(fields.get(0).getText());
                    double y = Double.parseDouble(fields.get(1).getText());
                    double w = Double.parseDouble(fields.get(2).getText());
                    double h = Double.parseDouble(fields.get(3).getText());
                    myObject.setX(x);
                    myObject.setY(y);
                    myObject.setSize(w, h);
                    GameObjectEditor.this.setVisible(false);
                }
                catch (NumberFormatException nf) {
                    showError("Could not parse values");
                }

            }
        });
        JButton addStrategy = new JButton("Add Strategy");
        addStrategy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                System.out.println("add strategy");
                // TODO
            }

        });
//        final JList list = new JList(myStrategies.toArray());
////        DefaultListModel myListModel = new DefaultListModel(list);
//        list.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged (ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting() && list.getSelectedIndex() >= 0) {
//                    Class item = myStrategies.get(list.getSelectedIndex());
//                    try {
//                        myObject.addStrategy((UpdateStrategy) item.newInstance());
//                    } catch (InstantiationException e1) {
//                        e1.printStackTrace();
//                    } catch (IllegalAccessException e1) {
//                        // TODO Auto-generated catch block
//                        e1.printStackTrace();
//                    }
//                    System.out.println("here");
//                }
//            }
//        });
//        JScrollPane addStrategy = new JScrollPane(list);
//        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        list.setVisibleRowCount(4);
        myBottom.add(addStrategy);
        myBottom.add(accept);
        return myBottom;

    }
    /**
     * shows an error on the popup, doesn't print stack
     * @param msg
     */
    private void showError (String msg) {
        JLabel errormsg = new JLabel(msg);
        errormsg.setForeground(Color.RED);
        add(errormsg, FlowLayout.CENTER);
        pack();
        repaint();
    }

    class HintTextField extends JTextField implements FocusListener {

        private final String myHint;

        public HintTextField (final String hint) {
            super(hint);
            myHint = hint;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained (FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText("");
            }
        }

        @Override
        public void focusLost (FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(myHint);
            }
        }

        @Override
        public String getText () {
            String typed = super.getText();
            return typed.equals(myHint) ? myHint : typed;
        }
    }
}
