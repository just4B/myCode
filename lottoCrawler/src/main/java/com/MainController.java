/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component("main")
public class MainController {
    
    final Logger logger = Logger.getLogger(MainController.class);
    
    @Resource(name = "httpRequest")
    private CustomHttpRequest httpRequest;
    
    @Resource(name = "persitence")
    private Persistence persistance;
    
    @Autowired
    @Resource(name="timeCounter")
    private LotteryTimeCounter timeCounter;
    
    public void run () throws InterruptedException {
        this.logger.info("Main method launched");
 
        if (ApplicationState.UPDATE_SINGLE_DATE) {
            this.timeCounter.setEndDate(ApplicationState.DATE_TO_UPDATE);
            this.timeCounter.setStartDate(ApplicationState.DATE_TO_UPDATE);
        } else {
            if (ApplicationState.START_IS_SET) {
                this.timeCounter.setStartDate(ApplicationState.START_DATE);
            }
            if (ApplicationState.END_IS_SET) {
                this.timeCounter.setEndDate(ApplicationState.END_DATE);
            }
        }
        
        httpRequest.setUrl(ApplicationState.URL);
        httpRequest.setRequestMethod("GET");
        httpRequest.setUrlQueryProperty("op", "");
        
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int emptyCallCounter = 0;
        while (this.timeCounter.countDateToCheck() != null) {
               if (emptyCallCounter >= 10) {
                   break;
               }
               this.logger.info(formater.format(this.timeCounter.getDateToCheck()));
               httpRequest.setUrlQueryProperty("data_losowania[date]", formater.format(this.timeCounter.getDateToCheck()));
               httpRequest.runRequest();
               Document htmlDoc = Jsoup.parse(httpRequest.getResponseBody());
               
               Elements balls = htmlDoc.select("div.sortrosnaco div.yellowball");
               if (balls.size() == 0) {
                   emptyCallCounter++;
                   continue;
               }
               emptyCallCounter = 0;
               for (Element ball : balls) {
                    numbers.add(Integer.parseInt(ball.text()));
               }
               this.persistance.setLotteryDate(this.timeCounter.getDateToCheck());
               this.persistance.setLotteryType(ApplicationState.LOTTERY_TYPE);
               this.persistance.setNumbers(numbers);
               this.persistance.persist(ApplicationState.FORCE_UPDATE);
               numbers.clear();
               Thread.sleep(ApplicationState.CALL_BREAK);
        }
    }    
}
