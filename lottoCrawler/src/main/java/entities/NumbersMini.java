package entities;
// Generated Dec 29, 2015 2:35:46 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import entities.Lottery;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

/**
 * Numbers generated by hbm2java
 */
@Entity
@Table(name="numbersmini"
    ,schema="container"
    , uniqueConstraints = @UniqueConstraint(columnNames="lotteryid") 
)
public class NumbersMini implements java.io.Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    @Column(name="numberone", nullable=false)
    private int numberone;
    @Column(name="numbertwo", nullable=false)
    private int numbertwo;
    @Column(name="numberthree", nullable=false)
    private int numberthree;
    @Column(name="numberfour", nullable=false)
    private int numberfour;
    @Column(name="numberfive", nullable=false)
    private int numberfive;
    @Column(name="lotteryid", unique=true)
    private Integer lotteryid;
    
    public NumbersMini() {}
    
    public NumbersMini (ArrayList<Integer> numbers) {
        this.numberone = numbers.get(0);
        this.numbertwo = numbers.get(1);
        this.numberthree = numbers.get(2);
        this.numberfour = numbers.get(3);
        this.numberfive = numbers.get(4);
    }
    
    public NumbersMini (int id, ArrayList<Integer> numbers) {
        this.id = id;
        this.numberone = numbers.get(0);
        this.numbertwo = numbers.get(1);
        this.numberthree = numbers.get(2);
        this.numberfour = numbers.get(3);
        this.numberfive = numbers.get(4);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumberone() {
        return this.numberone;
    }
    
    public void setNumberone(int numberone) {
        this.numberone = numberone;
    }
    
    public int getNumbertwo() {
        return this.numbertwo;
    }
    
    public void setNumbertwo(int numbertwo) {
        this.numbertwo = numbertwo;
    }
    
    public int getNumberthree() {
        return this.numberthree;
    }
    
    public void setNumberthree(int numberthree) {
        this.numberthree = numberthree;
    }
    
    public int getNumberfour() {
        return this.numberfour;
    }
    
    public void setNumberfour(int numberfour) {
        this.numberfour = numberfour;
    }

    
    public int getNumberfive() {
        return this.numberfive;
    }
    
    public void setNumberfive(int numberfive) {
        this.numberfive = numberfive;
    }
   
    public Integer getLotteryid() {
        return this.lotteryid;
    }
    
    public void setLotteryid(Integer lotteryid) {
            this.lotteryid = lotteryid;
    }

}


