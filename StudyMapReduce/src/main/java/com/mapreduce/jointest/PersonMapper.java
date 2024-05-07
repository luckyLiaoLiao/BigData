package com.mapreduce.jointest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PersonMapper extends Mapper<LongWritable,Text,PersonInfo,PersonInfo>{

    PersonInfo personInfo = new PersonInfo();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, PersonInfo, PersonInfo>.Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split(",");

        personInfo.setId(split[0]);
        personInfo.setName(split[1]);
        personInfo.setAge(Integer.parseInt(split[2]));
        personInfo.setAddress("");
        personInfo.setFlag("person");

        context.write(personInfo,personInfo);
    }
}
