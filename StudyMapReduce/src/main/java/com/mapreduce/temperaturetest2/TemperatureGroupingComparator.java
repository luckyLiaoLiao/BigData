package com.mapreduce.temperaturetest2;

import com.mapreduce.temperaturetest.Temperature;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/***
 * 自定义reduce端分类比较器
 * 1）自定义类要继承writableComparator
 * 2）自定义类中实现空构造，空构造中要调用父构造来创建实例
 * 3）自定义类中实现compare方法
 */
public class TemperatureGroupingComparator extends WritableComparator {
    public TemperatureGroupingComparator(){
        super(Temperature.class,true);
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
//        相同年月的数据放在一起
        Temperature a1 = (Temperature)a;
        Temperature b1 = (Temperature)b;

        if (a1.getYear().compareTo(b1.getYear())==0){
            return a1.getMonth().compareTo(b1.getMonth());
        }
        return a1.getYear().compareTo(b1.getYear());
    }
}
