/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapred;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;


public class EmplRecordReader extends RecordReader<LongWritable, Text> {

    LineRecordReader in;
    LongWritable key;
    Text txt;
    Long position;
    Long end;
    
    public EmplRecordReader (InputSplit is, TaskAttemptContext tac) throws IOException, InterruptedException {
        this.initialize(is, tac);
    }
            
    @Override
    public void initialize(InputSplit is, TaskAttemptContext tac) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) is;
        position = 0L;
        end = split.getLength();
        
        Configuration job = tac.getConfiguration();
        final Path file = split.getPath();
        FileSystem fs = file.getFileSystem(job);
        FSDataInputStream fileIn = fs.open(split.getPath());
        
        in = new LineRecordReader();
        in.initialize(is, tac);
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        in.nextKeyValue();
        key = in.getCurrentKey();
        txt = in.getCurrentValue();
        if (key == null || txt == null) {
            return false;
        }
        position = key.get();
        return true;
    }

    @Override
    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return txt;
        
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return ((float)position/end);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
    
}
