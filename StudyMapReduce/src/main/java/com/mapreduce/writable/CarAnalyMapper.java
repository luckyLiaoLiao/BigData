package com.mapreduce.writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CarAnalyMapper extends Mapper<LongWritable, Text,Text,CarInfo> {

    private Text outputKey = new Text();

    private CarInfo outputValue = new CarInfo();



    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, CarInfo>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split(",");

        outputKey.set(split[1]);

        outputValue.setCar(split[1]);
        outputValue.setAvgSpeed(Double.valueOf(split[2]));
        outputValue.setTotalKm(Double.valueOf(split[3]));

        context.write(outputKey,outputValue);

    }
}
