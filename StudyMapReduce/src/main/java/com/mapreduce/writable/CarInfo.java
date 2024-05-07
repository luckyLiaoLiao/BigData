package com.mapreduce.writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CarInfo implements Writable {

    public CarInfo(){

    }

    private String car;
    private Double avgSpeed;
    private Double totalKm;

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Double getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(Double totalKm) {
        this.totalKm = totalKm;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "car='" + car + '\'' +
                ", avgSpeed=" + avgSpeed +
                ", totalKm=" + totalKm +
                '}';
    }

    //    序列化数据
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(car);
        dataOutput.writeDouble(avgSpeed);
        dataOutput.writeDouble(totalKm);
    }

//    反序列化数据
    @Override
    public void readFields(DataInput dataInput) throws IOException {
//        要保证序列化和反序列化的顺序完全一致
        this.car = dataInput.readUTF();
        this.avgSpeed = dataInput.readDouble();
        this.totalKm = dataInput.readDouble();
    }
}
