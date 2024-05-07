package com.mapreduce.wordcount3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/***
 * Mapper
 * 自定义类继承自Mapper抽象类，实现map方法
 * KEYIN：当前这条数据，相对于整个文件的偏移量
 * VALUEIN：当前文件的一行数据
 * KEYOUT, VALUEOUT：写出数据的k和v
 * 其中LongWritable,Text, IntWritable 分别long、String，int的包装类
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    Text returnKey = new Text();
    IntWritable returnValue = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
//        hello zhangsan
        String line = value.toString();

//        切分单词
        String[] words = line.split(" ");

//        写出数据
        for (String word : words) {
            returnKey.set(word);
            context.write(returnKey,returnValue);
        }
    }
}
