/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapred;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class DepDateGroupingComparator extends WritableComparator {
    
    public DepDateGroupingComparator() {
        super(DepDatePair.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2) {
        DepDatePair valueOne = (DepDatePair) wc1;
        DepDatePair vaueTwo = (DepDatePair) wc2;
        return valueOne.getDepartment().compareTo(vaueTwo.getDepartment());
    }
}
