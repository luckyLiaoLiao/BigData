package com.mapreduce.orderanaly2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class OrderPartitioner extends Partitioner <Order, Text>{
    @Override
    public int getPartition(Order order, Text text, int numPartitions) {
//        先获取dt
        String dt = order.getDt();
        return (dt.hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
