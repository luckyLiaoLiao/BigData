package com.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    IntWritable total = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        /***
         * 这里的key就是形如hello这样的单词，values就是后面的那一堆1，
         * 即从map拉取过来的是(hello,1)(hello,1)(hello,1)(hello,1)在这里是(hello,<1,1,1,1>)
         * reduce task是按照key一组一组处理的。values分别是hello对应的那一堆1，hdfs对应的那一堆1等
         */

        int sum = 0;

        for (IntWritable value : values) {
            sum += value.get();
        }

        total.set(sum);

        context.write(key, total);
    }
}
