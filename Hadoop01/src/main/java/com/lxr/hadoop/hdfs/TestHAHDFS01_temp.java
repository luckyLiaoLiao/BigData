package com.lxr.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class TestHAHDFS01_temp {

    public static FileSystem fs = null;

    @Before
    public void conn() throws Exception {
        Configuration conf = new Configuration();
        fs = FileSystem.get(URI.create("hdfs://mycluster"), conf, "root");

    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        fs = FileSystem.get(URI.create("hdfs://mycluster"), conf, "root");


//        在HDFS中创建文件
        mkdirOnHDFS("/testdir");

//        向HDFS中上传文件.若testdir这个文件夹不存在，会先创建这个文件夹，再创建文件。
//        writeToHDFS("./data/data.txt","/testdir/data.txt");

//        对HDFS重命名
//        renameHDFSFile("/testdir/data.txt","/testdir/text.txt");

//        获取HDFS文件详细信息
//        getHDFSFileInfos("/testdir/text.txt");

//        读取HDFS文件数据
//        readFileFromHDFS("/testdir/text.txt");
    }

    @Test
    public void listHDFSFile() throws Exception {
        listHDFSFile("/");
    }

    private static void listHDFSFile(String hdfsPath) throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path(hdfsPath));
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isDirectory()) {
                listHDFSFile(fileStatus.getPath().toString());
            }
            System.out.println(fileStatus.getPath());
        }
    }

    private static void mkdirOnHDFS(String strPath) throws Exception {
        Path path = new Path(strPath);
        if (fs.exists(path)) {
            System.out.println("当前路径已存在：" + strPath);
            return;
        }

        boolean result = fs.mkdirs(path);
        if (result) {
            System.out.println("创建成功：" + strPath);
        } else {
            System.out.println("创建失败：" + strPath);
        }
    }

    @Test
    private static void readFileFromHDFS() throws Exception {
        String hdfsFilePath = "/testdir";
//        读取HDFS文件数据
        Path path = new Path(hdfsFilePath);
        FSDataInputStream in = fs.open(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String newLine = "";
        while ((newLine = br.readLine()) != null) {
            System.out.println(newLine);
        }

        br.close();
        in.close();
    }

    @Test
    private static void getHDFSFileInfos() throws Exception {
        String hdfsFilePath = "/testdir/text.txt";
        Path path = new Path(hdfsFilePath);
        RemoteIterator<LocatedFileStatus> listFileIt = fs.listFiles(path, true);
        while (listFileIt.hasNext()) {
            LocatedFileStatus fileStatus = listFileIt.next();
            System.out.println("文件详细信息如下");
            System.out.println("权限：" + fileStatus.getPermission());
            System.out.println("所有者：" + fileStatus.getOwner());
            System.out.println("大小：" + fileStatus.getLen());
            System.out.println("块大小：" + fileStatus.getBlockSize());
            System.out.println("文件名：" + fileStatus.getPath());

            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                System.out.println("block信息：" + blockLocation);
            }
        }

    }

    private static void renameHDFSFile(String hdfsOldFileName, String hdfsNewFileName) throws IOException {
        fs.rename(new Path(hdfsOldFileName), new Path(hdfsNewFileName));
        System.out.println("重命名成功");
    }

    private static void writeToHDFS(String localPath, String hdfsPath) throws Exception {
        Path path = new Path(hdfsPath);
        if (fs.exists(path)) {
            fs.delete(path, true);
        }

        fs.copyFromLocalFile(false, true, new Path(localPath), path);
        System.out.println("写入文件成功");
    }





    @Test
    public void listHDFSFiles(){
        System.out.println();
    }


}
