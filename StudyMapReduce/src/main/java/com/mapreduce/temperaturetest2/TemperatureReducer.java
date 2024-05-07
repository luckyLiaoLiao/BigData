package com.mapreduce.temperaturetest2;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TemperatureReducer extends Reducer<Temperature, Temperature, Temperature, NullWritable> {
    int cnt;
    HashMap<String,String> hm = new HashMap<>();
    ArrayList<Temperature> list = new ArrayList<>();
    @Override
    protected void reduce(Temperature key, Iterable<Temperature> values, Reducer<Temperature, Temperature, Temperature, NullWritable>.Context context) throws IOException, InterruptedException {

        String day = "";
        int cnt = 0;
        Iterator<Temperature> iterator = values.iterator();

        while (iterator.hasNext()){
            Temperature next = iterator.next();
            if (cnt==0){
                context.write(key,NullWritable.get());
                day = next.getDay();
                cnt +=1;
            }else{
                if (!day.equals(next.getDay())){
                    context.write(key,NullWritable.get());
                    cnt+=1;
                    break;//这里输出两条以后就break了，不需要后续的处理了
                }
            }
        }
    }
}
