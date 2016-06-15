/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 *
 * @author just4b
 */
public class ForegroundColorListener implements ActionListener {

    private JTextArea textArea;
    
    public ForegroundColorListener (JTextArea textArea) {
        this.textArea = textArea;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String color = menuItem.getName();
        if (color.equals("blue")) {
            this.textArea.setForeground(Color.BLUE);
        } else if (color.equals("yellow")) {
            this.textArea.setForeground(Color.YELLOW);
        } else if (color.equals("orange")) {
            this.textArea.setForeground(Color.ORANGE);
        } else if (color.equals("red")) {
            this.textArea.setForeground(Color.RED);
        } else if (color.equals("white")) {
            this.textArea.setForeground(Color.WHITE);
        } else if (color.equals("black")) {
            this.textArea.setForeground(Color.BLACK);
        } else if (color.equals("green")) {
            this.textArea.setForeground(Color.GREEN);
        }
        
        
    }
    
}
