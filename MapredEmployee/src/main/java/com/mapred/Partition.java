
package com.mapred;

import org.apache.hadoop.mapreduce.Partitioner;


public class Partition extends Partitioner {

    @Override
    public int getPartition(Object key, Object value, int i) {
        DepDatePair depKey = (DepDatePair)key;
        return depKey.getDepartment().get() - 1;
    }
    
}
