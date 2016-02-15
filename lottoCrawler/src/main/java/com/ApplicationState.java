
package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class ApplicationState {

    public static boolean UPDATE_SINGLE_DATE = false;
    public static Date DATE_TO_UPDATE = null;
    public static boolean FORCE_UPDATE = false;
    public static boolean START_IS_SET = false;
    public static Date START_DATE = null;
    public static boolean END_IS_SET = false;
    public static Date END_DATE = null;
    public static boolean CONTINUE = false;
    public static int LOTTERY_TYPE = 1;
    public static String QUERY_SELECTOR = "";
    public static String URL = "http://www.lotto.pl/--Type--/wyniki-i-wygrane/wyszukaj";
    public static Map<String, Integer> TYPES = new HashMap<String, Integer>();
    public static ArrayList<String> TYPE_LIST = new ArrayList<String>();
    public static ArrayList<String> QUERY_LIST = new ArrayList<String>();
    public static long CALL_BREAK = 500;
       
    static {
        TYPES.put("lotto", 0);
        TYPES.put("mini-lotto", 1);
        TYPES.put("multi-multi", 2);
        TYPE_LIST.add("lotto");
        TYPE_LIST.add("mini-lotto");
        TYPE_LIST.add("multi-multi");
        QUERY_LIST.add("div.sortrosnaco div.yellowball");
        QUERY_LIST.add("div.sortrosnaco div.yellowball");
        QUERY_LIST.add("div.sortrosnaco div.yellowball");
    }
    
    
    public static String showCurrentState () {
        StringBuilder output = new StringBuilder();
        output.append("\n Current application state:");
        output.append("\nUPDATE_SINGLE_DATE = ");
        output.append(UPDATE_SINGLE_DATE);
        output.append("\nDATE_TO_UPDATE = ");
        output.append(DATE_TO_UPDATE);
        output.append("\nFORCE_UPDATE = ");
        output.append(FORCE_UPDATE);
        output.append("\nSTART_IS_SET = ");
        output.append(START_IS_SET);
        output.append("\nSTART_DATE = ");
        output.append(START_DATE);
        output.append("\nEND_IS_SET = ");
        output.append(END_IS_SET);
        output.append("\nEND_DATE = ");
        output.append(END_DATE);
        output.append("\nCONTINUE = ");
        output.append(CONTINUE);
        output.append("\nLOTTERY_TYPE = ");
        output.append(LOTTERY_TYPE);
        output.append("\nQUERY_SELECTOR = ");
        output.append(QUERY_SELECTOR);
        output.append("\nURL = ");
        output.append(URL);
        output.append("\nCALL_BREAK = ");
        output.append(CALL_BREAK);
        return output.toString();
    }
    
    public static void resetApplicationState () {
        UPDATE_SINGLE_DATE = false;
        DATE_TO_UPDATE = null;
        FORCE_UPDATE = false;
        START_IS_SET = false;
        START_DATE = null;
        END_IS_SET = false;
        END_DATE = null;
        CONTINUE = false;
        LOTTERY_TYPE = 1;
        QUERY_SELECTOR = "";
        URL = "";
        CALL_BREAK = 500;
    }
    
}
