package com.mapreduce.writable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.DecimalFormat;

//如果不想输出key，可以把第三个参数的Text改成NullWritable

public class CarAnalyReducer extends Reducer<Text, CarInfo, Text, CarInfo> {

    private CarInfo carInfo = new CarInfo();


    @Override
    protected void reduce(Text key, Iterable<CarInfo> values, Reducer<Text, CarInfo, Text, CarInfo>.Context context) throws IOException, InterruptedException {

        int cnt = 0;
        double totalSpeed = 0;
        double totalKm = 0;

        for (CarInfo value : values) {
            totalSpeed += value.getAvgSpeed();
            totalKm += value.getTotalKm();
            cnt += 1;
        }

        DecimalFormat df = new DecimalFormat("#.00");
        Double avgSpeed = Double.valueOf(df.format(totalSpeed / cnt));

//        组织写出对象
        carInfo.setCar(key.toString());
        carInfo.setAvgSpeed(avgSpeed);
        carInfo.setTotalKm(totalKm);

        context.write(key,carInfo);
    }
}
