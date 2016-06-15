
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class App {

    
    public static JFrame frame;
    
    public static void addMenuItem(JMenu place, 
                              JMenuItem menuItem,
                              String name,
                              Dimension dimension, 
                              ActionListener listener,
                              KeyStroke stroke,
                              Icon icon) {
        menuItem.setName(name);
        if (dimension != null) {
            menuItem.setPreferredSize(dimension);
        }
        if (stroke != null) {
            menuItem.setAccelerator(stroke);
        }
        if (listener != null){
            menuItem.addActionListener(listener);
        }
        if (icon != null) {
            menuItem.setIcon(icon);
        }        
        place.add(menuItem);
    }
    
    public static void addSeparator (JMenu place, 
                                     JSeparator separator, 
                                     Dimension dimension, 
                                     Color background ) {
        if (dimension != null) {
            separator.setPreferredSize(dimension);
        }
        if (background != null){
            separator.setBackground(background);
        }
        place.add(separator);
    }
    
    private static void createAndShowGUI() {
        App.frame = new JFrame("Brak tytułu");
        App.frame.setLayout(new BorderLayout(100,100));
        App.frame.setPreferredSize(new Dimension(600, 600));
        App.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
             
        JScrollPane textScroll = new JScrollPane();
        Font font = new Font("Verdana", Font.PLAIN, 14);
        JTextArea textArea = new JTextArea();  
        textArea.setFont(font);
        textScroll.setViewportView(textArea);
        
        App.frame.add(textScroll, BorderLayout.CENTER);
        
        OpenListener openListener = new OpenListener(App.frame, textArea);
        SeveListener saveListener = new SeveListener(App.frame, textArea, SeveListener.SAVE);
        SeveListener saveAsListener = new SeveListener(App.frame, textArea, SeveListener.SVAE_AS);
        ExitListener exitListener = new ExitListener();
        EditListener schoolEdit = new EditListener(textArea, EditListener.EDIT_SCHOOL);
        EditListener workEdit = new EditListener(textArea, EditListener.EDIT_WORK);
        EditListener homeEdit = new EditListener(textArea, EditListener.EDIT_HOME);
        SizeListener sizeListener = new SizeListener(textArea);
        ForegroundColorListener fColorListener = new ForegroundColorListener(textArea);
        BackgroundColorListener bColorListener = new BackgroundColorListener(textArea);
        
        KeyStroke ctrlO = KeyStroke.getKeyStroke("control O");
        KeyStroke ctrlS = KeyStroke.getKeyStroke("control S");
        KeyStroke ctrlA = KeyStroke.getKeyStroke("control shift A");
        KeyStroke ctrlX = KeyStroke.getKeyStroke("control shift X");
        KeyStroke accA = KeyStroke.getKeyStroke("control shift B");
        KeyStroke accP = KeyStroke.getKeyStroke("control shift P");
        KeyStroke accD = KeyStroke.getKeyStroke("control shift D");
        
        CustomIcon blueCircle = new CustomIcon(Color.BLUE, 170, 3, 12, 12);
        CustomIcon yellowCircle = new CustomIcon(Color.YELLOW, 170, 3, 12, 12);
        CustomIcon orangeCircle = new CustomIcon(Color.ORANGE, 170, 3, 12, 12);
        CustomIcon redCircle = new CustomIcon(Color.RED, 170, 3, 12, 12);
        CustomIcon whiteCircle = new CustomIcon(Color.WHITE, 170, 3, 12, 12);
        CustomIcon blackCircle = new CustomIcon(Color.BLACK, 170, 3, 12, 12);
        CustomIcon greenCircle = new CustomIcon(Color.GREEN, 170, 3, 12, 12);
        
        JMenuBar menuBar = new JMenuBar();
                
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu optionsMenu = new JMenu("Options");
       
        
        App.addMenuItem(fileMenu, new JMenuItem("Open"), "", new Dimension(200, 20), openListener, ctrlO, null);
        App.addMenuItem(fileMenu, new JMenuItem("Save"), "", new Dimension(200, 20), saveListener, ctrlS, null);
        App.addMenuItem(fileMenu, new JMenuItem("Save as"), "", new Dimension(200, 20), saveAsListener, ctrlA, null);
        App.addSeparator(fileMenu, new JSeparator(), new Dimension(180, 5), new Color(255, 0, 0));
        App.addMenuItem(fileMenu, new JMenuItem("Exit"), "", new Dimension(200, 20), exitListener, ctrlX, null);
              
        
        JMenu adressItem = new JMenu("Adress");
        App.addMenuItem(adressItem, new JMenuItem("Szkoła"), "", new Dimension(200, 20), schoolEdit, accA, null);
        App.addMenuItem(adressItem, new JMenuItem("Dom"), "", new Dimension(200, 20), homeEdit, accD, null);
        App.addMenuItem(adressItem, new JMenuItem("Praca"), "", new Dimension(200, 20), workEdit, accP, null);
        editMenu.add(adressItem);
        
        
        
        JMenu foregroundItem = new JMenu("Foreground");
        App.addMenuItem(foregroundItem, new JMenuItem("Blue"), "blue", new Dimension(200, 20), fColorListener, null, blueCircle);
        App.addMenuItem(foregroundItem, new JMenuItem("Yellow"), "yellow", new Dimension(200, 20), fColorListener, null, yellowCircle);
        App.addMenuItem(foregroundItem, new JMenuItem("Orange"), "orange", new Dimension(200, 20), fColorListener, null, orangeCircle);
        App.addMenuItem(foregroundItem, new JMenuItem("Red"), "red", new Dimension(200, 20), fColorListener, null, redCircle);
        App.addMenuItem(foregroundItem, new JMenuItem("White"), "white", new Dimension(200, 20), fColorListener, null, whiteCircle);
        App.addMenuItem(foregroundItem, new JMenuItem("Black"), "black", new Dimension(200, 20), fColorListener, null, blackCircle);
        App.addMenuItem(foregroundItem, new JMenuItem("Green"), "green", new Dimension(200, 20), fColorListener, null, greenCircle);
        optionsMenu.add(foregroundItem);
        
        JMenu backgroundItem = new JMenu("Background");
        App.addMenuItem(backgroundItem, new JMenuItem("Blue"), "blue", new Dimension(200, 20), bColorListener, null, blueCircle);
        App.addMenuItem(backgroundItem, new JMenuItem("Yellow"), "yellow", new Dimension(200, 20), bColorListener, null, yellowCircle);
        App.addMenuItem(backgroundItem, new JMenuItem("Orange"), "orange", new Dimension(200, 20), bColorListener, null, orangeCircle);
        App.addMenuItem(backgroundItem, new JMenuItem("Red"), "red", new Dimension(200, 20), bColorListener, null, redCircle);
        App.addMenuItem(backgroundItem, new JMenuItem("White"), "white", new Dimension(200, 20), bColorListener, null, whiteCircle);
        App.addMenuItem(backgroundItem, new JMenuItem("Black"), "black", new Dimension(200, 20), bColorListener, null, blackCircle);
        App.addMenuItem(backgroundItem, new JMenuItem("Green"), "green", new Dimension(200, 20), bColorListener, null, greenCircle);
        optionsMenu.add(backgroundItem);
      
        
        JMenu fontSizeMenu = new JMenu("Font size");
        for (int i = 8; i <= 24; i = i + 2 ) {
             App.addMenuItem(fontSizeMenu, new JMenuItem(i + " pts"), Integer.toString(i), null, sizeListener, null, null);
        }
        optionsMenu.add(fontSizeMenu);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(optionsMenu);
        
        App.frame.setJMenuBar(menuBar);
        
        //Display the window.
        App.frame.pack();
        App.frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
      
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        
    }
    
    
}