package com.mapreduce.temperaturetest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TemperatureMapper extends Mapper<LongWritable, Text,Temperature,Temperature> {
    Temperature temperature = new Temperature();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Temperature, Temperature>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split("\t");
        String year = split[0].split("-")[0];
        String month = split[0].split("-")[1];
        String day = split[0].split("-")[2];
        int temp = Integer.parseInt(split[1]);
        temperature.setYear(year);
        temperature.setMonth(month);
        temperature.setDay(day);
        temperature.setTemp(temp);

        context.write(temperature,temperature);
    }
}
