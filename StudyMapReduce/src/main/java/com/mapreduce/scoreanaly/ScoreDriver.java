package com.mapreduce.scoreanaly;

import com.mapreduce.orderanaly2.OrderPartitioner;
import com.mapreduce.temperaturetest.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ScoreDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        1. 创建配置及job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2. 设置Driver驱动类
        job.setJarByClass(ScoreDriver.class);

//        3. 设置Mapper和Reducer对应的类
        job.setMapperClass(ScoreMapper.class);
        job.setReducerClass(ScoreReducer.class);

//        4. 设置Mapper Key，Value类型
        job.setMapOutputKeyClass(StudentInfo.class);
        job.setMapOutputValueClass(Text.class);

//        5. 设置最终输出数据的key，value类型
        job.setOutputKeyClass(StudentInfo.class);
        job.setOutputValueClass(NullWritable.class);

//        设置指定输出格式化类
        job.setOutputFormatClass(MyOutputFormat.class);

//        6.设置数据输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("data/scoredata.txt"));
//        将写出数据成功的标志文件写到该目录，真正的数据在自定义格式化类中定义的文件
        FileOutputFormat.setOutputPath(job,new Path("output_score"));

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
