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
public class BackgroundColorListener implements ActionListener {

    private JTextArea textArea;
    
    public BackgroundColorListener (JTextArea textArea) {
        this.textArea = textArea;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        String color = menuItem.getName();
        if (color.equals("blue")) {
            this.textArea.setBackground(Color.BLUE);
        } else if (color.equals("yellow")) {
            this.textArea.setBackground(Color.YELLOW);
        } else if (color.equals("orange")) {
            this.textArea.setBackground(Color.ORANGE);
        } else if (color.equals("red")) {
            this.textArea.setBackground(Color.RED);
        } else if (color.equals("white")) {
            this.textArea.setBackground(Color.WHITE);
        } else if (color.equals("black")) {
            this.textArea.setBackground(Color.BLACK);
        } else if (color.equals("green")) {
            this.textArea.setBackground(Color.GREEN);
        }
        
        
    }
    
}
