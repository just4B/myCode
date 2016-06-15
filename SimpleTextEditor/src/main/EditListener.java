/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;


public class EditListener implements ActionListener {

    private JTextArea textArea;
    private int actionType;
    
    public static final int EDIT_SCHOOL = 1;
    public static final int EDIT_WORK = 2;
    public static final int EDIT_HOME = 3;
    
    public EditListener (JTextArea textArea, int actionType) {
        this.textArea = textArea;
        this.actionType = actionType;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int currentPosition = this.textArea.getCaretPosition();
        if (this.actionType == EditListener.EDIT_SCHOOL) {
            this.textArea.insert("Jakas dziwna 15, 01-400 Warszawa", currentPosition);
        } else if (this.actionType == EditListener.EDIT_WORK) {
            this.textArea.insert("Domaniewska 41, 01-600 Warszawa", currentPosition);
        } else if (this.actionType == EditListener.EDIT_HOME) {
            this.textArea.insert("Zielona 12 m. 10, 01-500 Warszawa", currentPosition);
        }
        
    }
    
}
