package com.mapreduce.jointest2;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class PersonReducer extends Reducer<Text, PersonInfo, PersonInfo, NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<PersonInfo> values, Reducer<Text, PersonInfo, PersonInfo, NullWritable>.Context context) throws IOException, InterruptedException {
//       为什么两个id相同的数据会分到一堆呢？是按照什么分堆的吗？如果是变量地址哈希的话，那么怎么那么精准的就能hash到同一组呢？
//        如果是普通类型，如int，String，则直接比较
//        如果 是对象，则会看对象里面的compareTo方法，不是看equals方法。
//        所以将自己定义的对象作为key的话，必须要实现WritableComparable接口，
//        因为这个compareTo方法纪要用于排序，也要用于对象是否相同的比较
        ArrayList<PersonInfo> personList = new ArrayList<>();
        String address = "";
        for (PersonInfo value : values) {
            if (value.getFlag().equals("address")){
                address = value.getAddress();
            }
            personList.add(new PersonInfo(value.getId(),value.getName(), value.getAge(), value.getAddress(), null));
        }

        for (PersonInfo personInfo : personList) {
            personInfo.setAddress(address);
            if (!personInfo.getName().isEmpty() && !personInfo.getAddress().isEmpty()){
                context.write(personInfo,NullWritable.get());
            }
        }
    }
}
