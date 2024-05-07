package com.mapreduce.temperaturetest2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TemperatureDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        1. 创建配置及job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2. 设置Driver驱动类
        job.setJarByClass(TemperatureDriver.class);

//        3. 设置Mapper和Reducer对应的类
        job.setMapperClass(TemperatureMapper.class);
        job.setReducerClass(TemperatureReducer.class);

//        4. 设置Mapper Key，Value类型
        job.setMapOutputKeyClass(Temperature.class);
        job.setMapOutputValueClass(Temperature.class);

//        5. 设置最终输出数据的key，value类型
        job.setOutputKeyClass(Temperature.class);
        job.setOutputValueClass(NullWritable.class);

//        设置自定义分区器
        job.setPartitionerClass(TemperaturePartitioner.class);

//        设置reduce task 个数
        job.setNumReduceTasks(3);

//        设置reduce端分组规则（注意分组是一个key一组，分区是一个或多个key一区）
        job.setGroupingComparatorClass(TemperatureGroupingComparator.class);



//        6.设置数据输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("data/tempdata.txt"));
        FileOutputFormat.setOutputPath(job,new Path("output_temp_comp"));

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
