package com.mapreduce.compresstest;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/***
 * MapReduce任务的驱动类
 * 可以配置任务执行的参数
 * Mapper、Reducer、分区、分组相关信息
 */

public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        1. 创建配置及job对象
        Configuration conf = new Configuration();

//        设置map端使用压缩格式
        conf.setBoolean("mapreduce.map.output.compress",true);
        conf.setClass("mapreduce.map.output.compress.codec", SnappyCodec.class, CompressionCodec.class);

        Job job = Job.getInstance(conf);

//        2. 设置Driver驱动类
        job.setJarByClass(WordCountDriver.class);

//        3. 设置Mapper和Reducer对应的类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

//        4. 设置Mapper Key，Value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

//        5. 设置最终输出数据的key，value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

//        6.设置数据输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("hdfs://mycluster/mr/input/data.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://mycluster/mr/output/compress"));

//        设置reduce端数据写出使用压缩格式
        FileOutputFormat.setCompressOutput(job,true);
        FileOutputFormat.setOutputCompressorClass(job, SnappyCodec.class);

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
