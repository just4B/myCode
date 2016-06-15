/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util.parser;

import java.io.IOException;


public class RomanNumberFormatException extends IOException {
    
    public RomanNumberFormatException() {
        super();
    }

    public RomanNumberFormatException(String message) {
        super(message);
    }
    
    public RomanNumberFormatException(String message, Throwable thrwbl) {
        super(message, thrwbl);
    }
    

}
