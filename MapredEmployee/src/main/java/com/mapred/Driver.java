
package com.mapred;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Driver {

    public static void start () throws IOException, InterruptedException, ClassNotFoundException {
        String emplFilePath = "empl.csv";
        String depFilePath = "dep.csv";
        String outFilePath = "mrEmploye.csv";
        
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://localhost:54310");
        conf.set("mapred.job.tracker", "localhost:54311");
    
        Job job = new Job(conf);
        job.setJarByClass(Driver.class);
        //job.setInputFormatClass(EmplInputFormat.class);
        
        job.setMapOutputKeyClass(DepDatePair.class);
        job.setMapOutputValueClass(MapWritable.class);
        
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);
        
        MultipleInputs.addInputPath(job, new Path(depFilePath), TextInputFormat.class, DepartmentMap.class);
        MultipleInputs.addInputPath(job, new Path(emplFilePath), EmplInputFormat.class, EmployeeMap.class);
        
        job.setPartitionerClass(Partition.class);
        job.setGroupingComparatorClass(DepDateGroupingComparator.class);
        job.setReducerClass(JoinReducer.class);
        job.setNumReduceTasks(5);
        
        FileSystem fileSys = FileSystem.get(conf);
        Path outputFile = new Path(outFilePath);
        fileSys.delete(outputFile, true);
        
        FileOutputFormat.setOutputPath(job, outputFile);
        System.exit(job.waitForCompletion(false) ? 0 : 1);
        System.out.println("finished");
    }
    
}
