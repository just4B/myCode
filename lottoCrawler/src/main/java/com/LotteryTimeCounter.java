/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;


@Component("timeCounter")
public class LotteryTimeCounter {
   
    private Calendar currentDate;
    private Calendar getCurrentDate () {
        return this.currentDate;
    }
    
    private Date dateToCheck;
    public Date getDateToCheck () {
        return this.dateToCheck;
    }
    
    private Date startDate;
    public void setStartDate (Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate () {
        return this.startDate;
    }
    
    private Date endDate;
    public void setEndDate (Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate () {
        return this.endDate;
    }    
    
    @PostConstruct
    public void initialize  () {
        this.currentDate = Calendar.getInstance();
        this.dateToCheck = this.currentDate.getTime();
        this.startDate = this.currentDate.getTime();
        try {
            this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
        } catch (Exception exc) {
            this.endDate = null;
        }
    }
    
    public Date countDateToCheck () {
       try {
            if (this.currentDate.getTime().after(this.startDate)) {
                this.currentDate.setTime(this.startDate);
            }
            if (this.currentDate.getTime().before(this.endDate) || this.endDate == null) {
                return null;
            }
            this.dateToCheck = this.currentDate.getTime();
            return this.dateToCheck;
       } finally {
            this.currentDate.add(Calendar.DAY_OF_YEAR, -1);
       }
    }
    
    
}
