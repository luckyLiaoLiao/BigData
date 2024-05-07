package com.mapreduce.jointest2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PersonInfo implements WritableComparable<PersonInfo> {

    public PersonInfo(){

    }

    public PersonInfo(String id, String name, int age, String address, String flag) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.flag = flag;
    }

    private String id;
    private String name;
    private int age;
    private String address;
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int compareTo(PersonInfo o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.id);
        dataOutput.writeUTF(this.name);
        dataOutput.writeInt(this.age);
        dataOutput.writeUTF(this.address);
        dataOutput.writeUTF(this.flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id = dataInput.readUTF();
        this.name = dataInput.readUTF();
       this.age =  dataInput.readInt();
        this.address = dataInput.readUTF();
        this.flag = dataInput.readUTF();
    }
}
