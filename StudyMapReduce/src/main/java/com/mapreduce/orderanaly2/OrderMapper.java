package com.mapreduce.orderanaly2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, Order, Text> {

    Order order = new Order();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Order, Text>.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split("\t");
        order.setOrderId(split[0]);
        order.setDt(split[1]);
        order.setProductName(split[2]);
        order.setAmount(Integer.parseInt(split[3]));
        order.setTotalCost(Double.parseDouble(split[4]));

       /* 1、返回值不同:parseInt 返回值是int型,valueof   返回值是Integer型
          2、valueof就是调用了parseInt方法的
          3、parseInt效率比valueof效率高*/

        context.write(order, value);

    }
}
