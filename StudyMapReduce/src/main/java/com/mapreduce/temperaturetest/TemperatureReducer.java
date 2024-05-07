package com.mapreduce.temperaturetest;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TemperatureReducer extends Reducer<Temperature,Temperature,Temperature, NullWritable> {
    int cnt;
    HashMap<String,String> hm = new HashMap<>();
    ArrayList<Temperature> list = new ArrayList<>();
    @Override
    protected void reduce(Temperature key, Iterable<Temperature> values, Reducer<Temperature, Temperature, Temperature, NullWritable>.Context context) throws IOException, InterruptedException {

        Iterator<Temperature> iterator = values.iterator();
        while (iterator.hasNext()){
            Temperature next = iterator.next();
            list.add(next);
        }

//        遍历list集合，来取出相同年月 不同日期的top2的两条数据
        for (Temperature temperature : list) {
            String year = temperature.getYear();
            String month = temperature.getMonth();
            String day = temperature.getDay();

            if (!hm.containsKey(year+"-"+month)){
                cnt = 1;
                context.write(temperature,NullWritable.get());
                hm.put(year+"-"+month,day +"," +cnt);
            }else{
//                TODO 为什么温度是由高到低排好序的呢？是因为map的时候默认排序了，
//                 而且shuffle的时候也默认排序了，所以拉取到reduce的时候就是拍好序的
                if (!day.equals(hm.get(year+"-"+month).split(",")[0])){
                    cnt = Integer.parseInt(hm.get(year + "-" + month).split(",")[1]);
                    cnt+=1;
                    if (cnt==2){
                        context.write(temperature,NullWritable.get());
                    }
                    hm.put(year+"-"+month,day+","+cnt);
                }
            }
        }
    }
}
