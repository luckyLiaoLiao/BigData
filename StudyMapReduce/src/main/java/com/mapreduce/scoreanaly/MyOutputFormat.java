package com.mapreduce.scoreanaly;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyOutputFormat extends FileOutputFormat<StudentInfo, NullWritable> {
    @Override
    public RecordWriter<StudentInfo, NullWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        return new MyRecordWriter(taskAttemptContext);
    }

}


//    实现reducer输出的k，v写出
class MyRecordWriter extends RecordWriter<StudentInfo, NullWritable> {
    FSDataOutputStream passDataOutputStream;
    FSDataOutputStream failDataOutputStream;
    public MyRecordWriter(TaskAttemptContext context) throws IOException {
        FileSystem fileSystem = FileSystem.get(context.getConfiguration());
//        创建输出流-针对不同文件都要创建
        passDataOutputStream = fileSystem.create(new Path("D:/MR/pass.txt"));
        failDataOutputStream = fileSystem.create(new Path("D:/MR/fail.txt"));

    }

//    将数据写出到文件
    @Override
    public void write(StudentInfo studentInfo, NullWritable nullWritable) throws IOException, InterruptedException {
        int score = studentInfo.getScore();
        if (score>=80){
            passDataOutputStream.writeBytes(score+"\n");
        }else {
            failDataOutputStream.writeBytes(score+"\n");
        }
    }

    //    关闭写出的流对象
    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStreams(passDataOutputStream,failDataOutputStream);
    }
}
