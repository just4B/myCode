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
public class OpenListener implements ActionListener {

    private JFrame frame;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    
    public OpenListener (JFrame frame, JTextArea textArea) {
        this.frame = frame;
        this.textArea = textArea;
        this.fileChooser = new JFileChooser();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (! FileService.getLastDir().equals("")) {
                this.fileChooser.setCurrentDirectory(new File(FileService.getLastDir()));
            }
            this.fileChooser.setName("Open");
            this.fileChooser.showOpenDialog(null);
            String file = this.fileChooser.getSelectedFile().getAbsolutePath();
            FileService.setFilePath(file);
            this.frame.setTitle("Plik: " + FileService.getFileName());
            this.textArea.setText(FileService.readFile());
        } catch (Exception exc) {
        
        }
        
    }
    
}
