
package com.mapred;

import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;


public class MRUtils {
    
    static final Text FIRST_NAME = new Text("firstName");
    static final Text LAST_NAME = new Text("lastName");
    static final Text AGE = new Text("age");
    static final Text BIRTH_DATE = new Text("birthDate");
    static final Text WORK_START = new Text("workStart");
    static final Text DEPARTMENT_ID = new Text("departmentId");
    static final Text SALARY = new Text("salary");
    
    static final Text DEPARTMENT_NAME = new Text("departmentName");
    
    
    public static void prepareEmployeeMap (Text value, MapWritable output) {
        String[] input = value.toString().trim().split(" ");
        if (input.length != 7) {
            output = null;
        }   
        output.put(FIRST_NAME, new Text(input[0]));
        output.put(LAST_NAME, new Text(input[1]));
        output.put(AGE, new Text(input[2]));
        output.put(BIRTH_DATE, new Text(input[3]));
        output.put(WORK_START, new Text(input[4]));
        output.put(DEPARTMENT_ID, new Text(input[5]));
        output.put(SALARY, new Text(input[6]));
    }
    
    public static void prepareDepartmentMap (Text value, MapWritable output) {
        String[] input = value.toString().trim().split(" ");
        if (input.length != 2) {
            output = null;
        }   
        output.put(DEPARTMENT_ID, new Text(input[0]));
        output.put(DEPARTMENT_NAME, new Text(input[1]));
    }
    
    
}
