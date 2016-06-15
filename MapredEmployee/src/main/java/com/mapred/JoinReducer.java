/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapred;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class JoinReducer extends Reducer<DepDatePair, MapWritable, LongWritable, Text> {
    
    Text outputValue = new Text();
    StringBuilder builder = new StringBuilder();
    String departmentName = "";
    Long index;
    
    protected void reduce(DepDatePair key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
        Iterator valuesIterator = values.iterator();
        if (valuesIterator.hasNext()) {
            this.index = 1L;
            MapWritable depMap = (MapWritable)valuesIterator.next();
            this.departmentName = depMap.get(MRUtils.DEPARTMENT_NAME).toString();
            if (this.departmentName == null) {
                this.departmentName = "DEP#NAME#ERROR";
            }
            while (valuesIterator.hasNext()) {
                 MapWritable map = (MapWritable)valuesIterator.next();
                 Set keySet = map.keySet();
                 for (Object singleKey : keySet) {
                     this.builder.append(map.get((Text)singleKey));
                     this.builder.append(",");
                 }
                 this.builder.append(this.departmentName);
                 this.builder.append(";");
                 this.outputValue.set(this.builder.toString());
                 context.write(new LongWritable(this.index++), outputValue);
                 this.builder.delete(0, this.builder.length());
            }
        }
       
        
    }
    
}
