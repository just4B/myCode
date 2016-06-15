package com.util.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class RomanNumRules {
    static HashMap<Integer, Character> romanNum  = new HashMap<Integer, Character>();
    static HashMap<Character, Integer> decValues  = new HashMap<Character, Integer>();
    static HashMap<Character, Integer>  rightRules  = new HashMap<Character, Integer> ();
    static ArrayList<Integer> relations = new ArrayList<Integer>();
    
    static {
        romanNum.put(1, 'I');
        romanNum.put(5, 'V');
        romanNum.put(10, 'X');
        romanNum.put(50, 'L');
        romanNum.put(100, 'C');
        romanNum.put(500, 'D');
        romanNum.put(1000, 'M');
        
        decValues.put('I', 1);
        decValues.put('V', 5);
        decValues.put('X', 10);
        decValues.put('L', 50);
        decValues.put('C', 100);
        decValues.put('D', 500);
        decValues.put('M', 1000);
        
        rightRules.put('I', 3);
        rightRules.put('V', 1);
        rightRules.put('X', 3);
        rightRules.put('L', 1);
        rightRules.put('C', 3);
        rightRules.put('D', 1);
        rightRules.put('M', 100);
        
        relations.add(1000);
        relations.add(500);
        relations.add(100);
        relations.add(50);
        relations.add(10);
        relations.add(5);
        relations.add(1);
    }
}
