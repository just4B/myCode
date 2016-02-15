
package com;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Value;

@Component("console")
public class ConsoleReader {
        
    @Value("${crawler.url}")
    public String url;
    
  
    
    final Logger logger = Logger.getLogger(ConsoleReader.class);
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static int optionCounter = 0;
    
    public boolean read (String[] args){
        try {
            ConsoleReader.optionCounter = 0;
            for (int i = 0; i < args.length; i++) {
                 if (args[i].equals("--help")) {
                     this.showOptions();
                     return false;
                 }
                 else if (args[i].equals("-update")) {
                     i++;
                     this.setUpdateOption(args[i]);
                     ConsoleReader.optionCounter++;
                     return true;
                 }
                 else if (args[i].equals("-force")) {
                     this.setForceOption();
                     ConsoleReader.optionCounter++;
                     continue;
                 }
                 else if (args[i].equals("-start")) {
                     i++;
                     this.setStartOption(args[i]);
                     ConsoleReader.optionCounter++;
                     continue;
                 }
                 else if (args[i].equals("-end")) {
                     i++;
                     this.setEndOption(args[i]);
                     ConsoleReader.optionCounter++;
                     continue;
                 }
                 else if (args[i].equals("-continue")) {
                     this.setConinueOption();
                     ConsoleReader.optionCounter++;
                     continue;
                 } else if (args[i].equals("-break")) {
                     i++;
                     this.setBreakOption(args[i]);
                     ConsoleReader.optionCounter++;
                     continue;
                 } else if (args[i].equals("-type")) {
                     i++;
                     if (!ApplicationState.TYPES.containsKey(args[i])) {
                         throw new Exception();
                     }                     
                     this.setType(args[i]);
                     ConsoleReader.optionCounter++;
                     continue;
                 }
                 
            }
            if (ConsoleReader.optionCounter == 0) {
                this.setConinueOption();
                this.setType("lotto");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Passed argument conteins error(s), please type --help fo more information");
        }
        return false;
    }
    
    private void showOptions () {
        StringBuilder outputString = new StringBuilder();
        outputString.append("\nOption available for application:\n");
        outputString.append("-update <date format: yyyy-MM-dd> - update single date (if selected, other option ignored) \n");
        outputString.append("-force - will update existing dates\n");
        outputString.append("-start <date format: yyyy-MM-dd> - set start date\n");
        outputString.append("-end <date format: yyyy-MM-dd> - set end date\n");
        outputString.append("-continue - (default setting) set start date from dateabase (if update or start i selected option will be ignored) \n");
        outputString.append("-type - (default value : lotto) avaible to choose: lotto, mini-lotto, multi-multi \n");
        outputString.append("-breaks - (default value : 500) value of miliseconds of brak between calls to site \n");
        System.out.println(outputString.toString());
    }  
    
    private void setUpdateOption (String passedDate) throws ParseException {
        ApplicationState.resetApplicationState();
        ApplicationState.UPDATE_SINGLE_DATE = true;
        ApplicationState.DATE_TO_UPDATE = dateFormat.parse(passedDate);
        ApplicationState.CONTINUE = false;
    }
    
    private void setForceOption () {
        ApplicationState.FORCE_UPDATE = true;     
    }
    
    private void setStartOption (String passedDate) throws ParseException {
        ApplicationState.START_IS_SET = true;
        ApplicationState.START_DATE = dateFormat.parse(passedDate);
        ApplicationState.CONTINUE = false;
    }
    
    private void setEndOption (String passedDate) throws ParseException {
        ApplicationState.END_IS_SET = true;
        ApplicationState.END_DATE = dateFormat.parse(passedDate);
    }    
    
    private void setConinueOption () {
        if (!ApplicationState.START_IS_SET && !ApplicationState.UPDATE_SINGLE_DATE) {
            ApplicationState.CONTINUE = true;
        }
    }
    
    private void setType (String type) {
            ApplicationState.LOTTERY_TYPE = ApplicationState.TYPES.get(type);
            ApplicationState.URL = ApplicationState.URL.replace("--Type--", ApplicationState.TYPE_LIST.get(ApplicationState.LOTTERY_TYPE));
            ApplicationState.QUERY_SELECTOR = ApplicationState.QUERY_LIST.get(ApplicationState.LOTTERY_TYPE);
    }
    
    private void setBreakOption (String breakValue) {
        ApplicationState.CALL_BREAK = Long.parseLong(breakValue);
    }
    
}
