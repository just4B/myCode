package main;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.ApplicationState;
import com.EntryPoint;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;


public class App 
{
    public static void main (String[] args ) {
      EntryPoint.app(args);
    }
}
