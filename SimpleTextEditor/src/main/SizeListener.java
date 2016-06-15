/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 *
 * @author just4b
 */
public class SizeListener implements ActionListener {

    private JTextArea textArea;
    
    public SizeListener (JTextArea textArea) {
        this.textArea = textArea;
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JMenuItem clicked = (JMenuItem)e.getSource();
            String name = clicked.getName(); 
            int fontSize = Integer.parseInt(name);
            Font font = new Font("Verdana", Font.PLAIN, fontSize);
            this.textArea.setFont(font);
        } catch (Exception exc) {
        }
    }
    
}
