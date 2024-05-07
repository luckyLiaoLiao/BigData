package com.mapreduce.writable;

import com.mapreduce.wordcount.WordCountDriver;
import com.mapreduce.wordcount.WordCountMapper;
import com.mapreduce.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CarAnalyDriver {

    public static void main(String[] args) throws Exception {
        //        1. 创建配置及job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2. 设置Driver驱动类
        job.setJarByClass(CarAnalyDriver.class);

//        3. 设置Mapper和Reducer对应的类
        job.setMapperClass(CarAnalyMapper.class);
        job.setReducerClass(CarAnalyReducer.class);

//        4. 设置Mapper Key，Value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CarInfo.class);

//        5. 设置最终输出数据的key，value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(CarInfo.class);

//        6.设置数据输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("data/cardata.txt"));
        FileOutputFormat.setOutputPath(job,new Path("caroutput"));

//        7.运行任务
//        true别扭表示打印任务执行过程中的详细信息
        boolean success = job.waitForCompletion(true);
        if (success){
            System.out.println("任务执行成功！");
        }else {
            System.out.println("任务执行失败！");
        }
    }
}
