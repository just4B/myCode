
package com;

import entities.Lottery;
import entities.NumbersLotto;
import entities.NumbersMini;
import entities.NumbersMulti;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("persitence")
public class Persistence {
    
     final Logger logger = Logger.getLogger(Persistence.class);
    
    @Autowired
    private SessionFactory hibSession;
    
    public Persistence () {
        this.lotteryType = -1;
    }
    
    private Date lotteryDate;
    public void setLotteryDate (Date lotteryDate) {
        this.lotteryDate = lotteryDate;
    }
    public Date getLotteryDate () {
        return this.lotteryDate;
    }
    
    private int lotteryType;
    public void setLotteryType (int lotteryType) {
        this.lotteryType = lotteryType;
    }
    public int getLotteryType () {
        return this.lotteryType;
    }
    
    private ArrayList<Integer> numbers;
    public void setNumbers (ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }
    public ArrayList<Integer> getNumbers () {
        return this.numbers;
    }
    
    private NumbersLotto numLotto;
    private NumbersMini numMini;
    private NumbersMulti numMulti;
    private Lottery lotteryObj;
    
    private boolean prepareLottery () {
        try {
            this.lotteryObj = new Lottery();
            this.lotteryObj.setLotterydate(this.lotteryDate);
            this.lotteryObj.setLotterytype(this.lotteryType);
        } catch (Exception exc) {
            this.logger.warn(exc.getMessage());
            return false;
        }
        return true;
    }
    
    private boolean prepareNumbers () {
        try {
            if (this.numbers.size() == 6) {
                this.numLotto = new NumbersLotto(this.numbers);
            } else if (this.numbers.size() == 5) {
               this.numLotto = new NumbersLotto(this.numbers);
            } else if (this.numbers.size() == 15) {
                this.numLotto = new NumbersLotto(this.numbers);
            } else {
                return false;
            }
            return true;
        } catch (Exception exc) {
            this.logger.warn(exc.getMessage());
            return false;
        }
    }
    
    
    private boolean prepareObjects () {
        if (this.lotteryDate == null || this.lotteryType < 0) {
            return false;
        }
        if (this.prepareNumbers() == false) {
            return false;
        }
        return this.prepareLottery();
        
    }   
    
    public boolean persist (boolean forceUpdate) {
        Session sess = this.hibSession.openSession();
        Transaction trans = sess.beginTransaction();
        try {
            if (! this.prepareObjects()) {
                return false;
            }
            Lottery lott = this.checkIfLotteryExists(this.lotteryDate, this.lotteryType);
            if (forceUpdate == false && lott != null) {
                return false;
            }else if (lott != null) {
                this.lotteryObj.setId(lott.getId());
            }
            sess.saveOrUpdate(this.lotteryObj);
            if (this.lotteryType == 0) {
                this.numLotto.setLotteryid(this.lotteryObj.getId());
                sess.save(this.numLotto);
            } else if (this.lotteryType == 1) {
                this.numMini.setLotteryid(this.lotteryObj.getId());
                sess.save(this.numMini);
            } else if (this.lotteryType == 2) {
                this.numMulti.setLotteryid(this.lotteryObj.getId());
                sess.save(this.numMulti);
            }
            trans.commit();
            return true;
        } catch (Exception exc) {
            this.logger.warn(exc.getMessage());
            trans.rollback();
            return false;
        }
    }  
    
    private Lottery checkIfLotteryExists (Date date, int type) {
        Session sess = this.hibSession.openSession();
        Transaction trans = sess.beginTransaction();
        Criterion crx = Restrictions.and(Restrictions.eq("lotterytype", this.lotteryType), 
                                         Restrictions.eq("lotterydate", this.lotteryDate));
        List<Object> lotteryList = sess.createCriteria(Lottery.class).add(crx)
                                                                 .setFirstResult(0)
                                                                 .setMaxResults(5)
                                                                 .list();
        if (lotteryList.isEmpty()) {
            return null;
        }
        return (Lottery)lotteryList.get(0);        
    }
    
    public Lottery load () {
        Session sess = this.hibSession.openSession();
        Transaction trans = sess.beginTransaction();
        try {
            Criterion crx = Restrictions.eq("lotterytype", this.lotteryType);
            List<Object> lottery = sess.createCriteria(Lottery.class).add(crx)
                                                                     .addOrder(Order.desc("lotterydate"))
                                                                     .setFirstResult(0)
                                                                     .setMaxResults(5)
                                                                     .list();
            if (lottery.isEmpty()) {
                return null;
            }
            this.lotteryObj = (Lottery)lottery.get(0);
            this.loadNumbers(this.lotteryObj.getId(), sess);
            trans.commit();
            return this.lotteryObj;
        } catch (Exception exc) {
            trans.rollback();
            this.logger.warn(exc.getMessage());
            return null;
        }
    }
    
    public Lottery load (Date date, int type) {
        Session sess = this.hibSession.openSession();
        Transaction trans = sess.beginTransaction();
        try {
            Criterion crx = Restrictions.and(Restrictions.eq("lotterytype", this.lotteryType), 
                                             Restrictions.eq("lotterydate", this.lotteryDate));
            List<Object> lottery = sess.createCriteria(Lottery.class).add(crx)
                                                                     .setFirstResult(0)
                                                                     .setMaxResults(5)
                                                                     .list();
            if (lottery.isEmpty()) {
                return null;
            }
            this.lotteryObj = (Lottery)lottery.get(0);
            this.loadNumbers(this.lotteryObj.getId(), sess);
            trans.commit();
            return this.lotteryObj;
        } catch (Exception exc) {
            trans.rollback();
            this.logger.warn(exc.getMessage());
            return null;
        }
    }  
    
    private void loadNumbers (int lotteryId, Session sess) {
        Criterion crx = Restrictions.eq("lotteryid", lotteryId);
        List<Object> numbers = null;
        if (this.lotteryType == 0) {
            numbers = sess.createCriteria(NumbersLotto.class).add(crx).list();
            this.numLotto = (NumbersLotto)numbers.get(0);
        } else if (this.lotteryType == 0) {
            numbers = sess.createCriteria(NumbersMini.class).add(crx).list();
            this.numMini = (NumbersMini)numbers.get(0);
        } else if (this.lotteryType == 0) {
            numbers = sess.createCriteria(NumbersMulti.class).add(crx).list();
            this.numMulti = (NumbersMulti)numbers.get(0);
        } 
    }
    
    
    
    
}
