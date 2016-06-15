package com.util.parser;


public class RomanNumberParser {
    
    private NumberParser parser = new NumberParser();
    private NumberParser getParser() {
        return this.parser;
    }
    
    private int intValue;
    public int getIntValue () {
        return this.intValue;
    }
    public void setIntValue (int value) {
        this.intValue = value;
    }
    
    private String romanFormat;
    public String getRomanFormat () {
        return this.romanFormat;
    }
    public void setRomanFormat (String formatedNumber) {
        this.romanFormat = formatedNumber;
    }
    
    public RomanNumberParser (){
        this.parser = new NumberParser();
        this.intValue = 0;
        this.romanFormat = "";
    }
    
    public RomanNumberParser (String formatedNumber) throws RomanNumberFormatException {
        this.parser = new NumberParser();
        this.intValue = this.parser.parseRomaFormat(formatedNumber);
        this.romanFormat = formatedNumber;
    }
    
    public RomanNumberParser (int value) throws RomanNumberValueException {
        if (value <= 0) {
            throw new RomanNumberValueException("Positive numbers only");
        }
        this.parser = new NumberParser();
        this.intValue = value;
        this.romanFormat = this.parser.parseArabicFormat(value);
    }
    
    public static String castToRomanFormat(int value) throws RomanNumberValueException {
        RomanNumberParser romanNum = new RomanNumberParser(value);
        return romanNum.romanFormat;
    }
    
    public static int intValue (String romanNumber) throws RomanNumberFormatException {
        RomanNumberParser romanNum = new RomanNumberParser();
        return  romanNum.getParser().parseRomaFormat(romanNumber.toUpperCase());
    }
    
    public static RomanNumberParser valueOf (int value ) throws RomanNumberValueException {
        return new RomanNumberParser(value);
    }
    
    public static RomanNumberParser valueOf (String value) throws RomanNumberFormatException {
        return new RomanNumberParser(value);
    }
    

    
}
