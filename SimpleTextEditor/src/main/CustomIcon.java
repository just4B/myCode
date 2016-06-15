/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.Icon;

/**
 *
 * @author just4b
 */
public class CustomIcon implements Icon {

    private Color colorType;
    private int startX;
    private int startY;
    private int width;
    private int height;
    private String text;
    
    public static final int CIRCLE = 1;
    public static final int TEXT = 2;
        
    public CustomIcon ( Color color, int startX, int startY, int width, int height) {
        this.width = width;
        this.height = height;
        this.colorType = color;
        this.startX = startX;
        this.startY = startY;
    }
  
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(this.colorType);
        g.fillOval(this.startX, this.startY, this.width, this.height);
    }

    @Override
    public int getIconWidth() {
        return this.width;
    }

    @Override
    public int getIconHeight() {
        return this.height;
    }
    
}
