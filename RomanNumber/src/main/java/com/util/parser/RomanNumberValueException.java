/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util.parser;

/**
 *
 * @author just4b
 */
public class RomanNumberValueException extends Exception {
    
    public RomanNumberValueException() {
        super();
    }

    public RomanNumberValueException(String message) {
        super(message);
    }
    
    public RomanNumberValueException(String message, Throwable thrwbl) {
        super(message, thrwbl);
    }
}
