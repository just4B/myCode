/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author just4b
 */
public class SeveListener implements ActionListener {

    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private int actionType;
    
    public static final int SAVE = 1;
    public static final int SVAE_AS = 2;
    
    public SeveListener (JFrame frame, JTextArea textArea, int actionType) {
        this.frame = frame;
        this.textArea = textArea;
        this.fileChooser = new JFileChooser();
        this.actionType = actionType;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       try {
            String file = "";
            if (this.actionType == SeveListener.SVAE_AS || 
                FileService.getFilePath().equals("") ) {
                if (! FileService.getLastDir().equals("")) {
                    this.fileChooser.setCurrentDirectory(new File(FileService.getLastDir()));
                }
                this.fileChooser.showSaveDialog(null);
                file = this.fileChooser.getSelectedFile().getAbsolutePath();
            } else {
                file = FileService.getFilePath();
            }
            FileService.setFilePath(file);
            this.frame.setTitle("Plik: " + FileService.getFileName());
            FileService.writeFile(this.textArea.getText());
       } catch (Exception exc) {
       
       } 
        
    }
    
}
