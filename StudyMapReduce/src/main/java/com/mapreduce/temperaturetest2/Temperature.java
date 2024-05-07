package com.mapreduce.temperaturetest2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Temperature implements WritableComparable<Temperature> {

    public Temperature() {

    }

    private String year;
    private String month;
    private String day;
    private Integer temp;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", temp=" + temp +
                '}';
    }

    @Override
    public int compareTo(Temperature o) {
        int yearCompare = this.getYear().compareTo(o.getYear());
        int monthCompare = this.getMonth().compareTo(o.getMonth());
        if (yearCompare == 0) {
            if (monthCompare == 0) {
                return this.getTemp() > o.getTemp() ? -1 : 1;
            }
            return monthCompare;
        }
        return yearCompare;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.year);
        dataOutput.writeUTF(this.month);
        dataOutput.writeUTF(this.day);
        dataOutput.writeInt(this.temp);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readUTF();
        this.month = dataInput.readUTF();
        this.day = dataInput.readUTF();
        this.temp = dataInput.readInt();
    }
}
