package com.mapreduce.scoreanaly;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StudentInfo implements WritableComparable<StudentInfo> {

    public  StudentInfo(){

    }

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(name);
        dataOutput.writeInt(score);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.name = dataInput.readUTF();
        this.score = dataInput.readInt();
    }

    @Override
    public int compareTo(StudentInfo o) {
        if (this.score>o.score){
            return -1;
        }else if (this.score<o.score){
            return 1;
        }else {
            return 0;
        }
    }
}
