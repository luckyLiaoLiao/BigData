package com.mapreduce.orderanaly2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/***
 * 自定义类，实现WritableComparable接口
 */
public class Order implements WritableComparable<Order> {
    public Order(){

    }

    private String orderId;
    private String dt;
    private String productName;
    private int amount;
    private double totalCost;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", dt='" + dt + '\'' +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", totalCost=" + totalCost +
                '}';
    }


    /***
     * 按照花费金额降序排序
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Order o) {
        if (this.totalCost>o.totalCost){
            return -1;
        }else if(this.totalCost<o.totalCost){
            return 1;
        }else {
            if (this.amount>o.totalCost){
                return -1;
            }else if (this.amount<o.amount){
                return 1;
            }else {
                return 0;
            }
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(dt);
        dataOutput.writeUTF(productName);
        dataOutput.writeInt(amount);
        dataOutput.writeDouble(totalCost);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        this.orderId = dataInput.readUTF();
        this.dt = dataInput.readUTF();
        this.productName = dataInput.readUTF();
        this.amount = dataInput.readInt();
        this.totalCost = dataInput.readDouble();
    }
}
