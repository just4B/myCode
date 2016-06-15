/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapred;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class DepartmentMap extends Mapper<LongWritable, Text, DepDatePair, MapWritable> {
    
    MapWritable outputMap = new MapWritable();
    DepDatePair depKey = new DepDatePair();
     
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        MRUtils.prepareDepartmentMap(value, outputMap);
        if (outputMap == null) {
            System.out.println("DepartmentMap >> bad row with value = " + value.toString());
            return;
        }
        
        IntWritable department = new IntWritable(Integer.parseInt(outputMap.get(MRUtils.DEPARTMENT_ID).toString()));
        depKey.setDepartment(department);
        depKey.setHireDate(null);
        context.write(depKey, outputMap);
    }
    
}
