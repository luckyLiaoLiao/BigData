package com.mapreduce.jointest;

import com.mapreduce.orderanaly.Order;
import com.mapreduce.orderanaly.OrderDriver;
import com.mapreduce.orderanaly.OrderMapper;
import com.mapreduce.orderanaly.OrderReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PersonDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        1. 创建配置及job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2. 设置Driver驱动类
        job.setJarByClass(PersonDriver.class);

//        3. 设置Mapper和Reducer对应的类
        MultipleInputs.addInputPath(job,new Path("data/person.txt"), TextInputFormat.class, PersonMapper.class);
        MultipleInputs.addInputPath(job,new Path("data/address.txt"), TextInputFormat.class, AddressMapper.class);

        job.setReducerClass(PersonReducer.class);

//        4. 设置Mapper Key，Value类型
        job.setMapOutputKeyClass(PersonInfo.class);
        job.setMapOutputValueClass(PersonInfo.class);

//        5. 设置最终输出数据的key，value类型
        job.setOutputKeyClass(PersonInfo.class);
        job.setOutputValueClass(NullWritable.class);
//        -------------------------------------------------------------------------------

//        6.设置数据输入和输出路径
        FileOutputFormat.setOutputPath(job,new Path("output_personaddress"));

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
