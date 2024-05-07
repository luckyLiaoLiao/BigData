package com.mapreduce.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        String key = text.toString();
        if ("zhangsan".equals(key) || "lisi".equals(key)){
            return 0;
        }
        return 1;
    }
}
