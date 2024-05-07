package com.mapreduce.orderanaly;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OrderDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        1. 创建配置及job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2. 设置Driver驱动类
        job.setJarByClass(OrderDriver.class);

//        3. 设置Mapper和Reducer对应的类
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

//        4. 设置Mapper Key，Value类型
        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(Text.class);

//        5. 设置最终输出数据的key，value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
//        -------------------------------------------------------------------------------

//        6.设置数据输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("data/orderdata.txt"));
        FileOutputFormat.setOutputPath(job,new Path("orderoutput"));

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
