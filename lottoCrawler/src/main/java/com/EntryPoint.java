
package com;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;


public class EntryPoint {
    
    public static void app (String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(configuration.Config.class);
        PropertyConfigurator.configure("src/main/resources/l4j.properties"); 
        ConsoleReader console = (ConsoleReader) context.getBean("console");
        
        System.out.println("\n\n########### START ###########\n\n");
        try {
            if (console.read(args) ==  true) {

                MainController main = (MainController) context.getBean("main");
                main.run();
            }
        } catch (Exception exc ){
            System.out.println(exc.getMessage());
        }
        System.out.println("\n\n########### END ###########\n\n");
    }
    
    
}
