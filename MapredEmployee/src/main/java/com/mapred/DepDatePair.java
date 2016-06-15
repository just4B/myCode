
package com.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;


public class DepDatePair implements WritableComparable<DepDatePair> {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    private IntWritable department;
    public IntWritable getDepartment() {
        return this.department;
    }
    public void setDepartment(IntWritable dep) {
        this.department = dep;
    }
    
    private Text hireDate;
    public Text getHireDate() {
        return this.hireDate;
    }
    public void setHireDate(Text date) {
        this.hireDate = date;

    }
    
    public DepDatePair () {
        this.department = new IntWritable();
        this.hireDate = new Text();
    }
    
    
    @Override
    public void write(DataOutput d) throws IOException {
        this.department.write(d);
        try {
            this.hireDate.write(d);
        } catch (Exception exc) {
            this.hireDate = null;
        }
       
    }

    @Override
    public void readFields(DataInput di) throws IOException {
       this.department.readFields(di);
       try {
            this.hireDate.readFields(di);
       } catch (Exception exc) {
            this.hireDate = null;
       }
    }

    @Override
    public int compareTo(DepDatePair t) {
        if (this.hireDate == null) {
            return -1;
        }
        if (t.getHireDate() == null) {
            return 1;
        }
        Date thisDate = null;
        Date otherDate = null;
        try {
            thisDate = dateFormat.parse(this.hireDate.toString());
        } catch (ParseException ex) {
            return -1;
        }
        
        try {
            otherDate = dateFormat.parse(t.getHireDate().toString());
        } catch (ParseException ex) {
            return 1;
        }
        if (thisDate.before(otherDate)) {
            return -1;
        } else if (thisDate.after(otherDate)) {
            return 1;
        }
        return 0;
    }
}
