package com.mapreduce.temperaturetest;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class TemperaturePartitioner extends Partitioner<Temperature, Temperature> {
    @Override
    public int getPartition(Temperature key, Temperature value, int numPartitioners) {
        String ym = key.getYear() + "-" + key.getMonth();
        return (ym.hashCode() & Integer.MAX_VALUE)%numPartitioners;
    }
}
