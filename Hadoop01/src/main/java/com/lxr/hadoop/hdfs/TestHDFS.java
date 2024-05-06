package com.lxr.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestHDFS {

    public Configuration conf = null;
    public FileSystem fs = null;

    /***
     * C/S架构的都会有建立连接-使用-断开连接这三个步骤，所以用Before、After注解实现
     * 客户端启动后怎么知道连接那个主机的哪个端口号呢？通过配置文件core-site.xml
     * 怎么解析xml文件呢？
     * @throws IOException
     */

    @Before
    public void conn() throws Exception {
        conf = new Configuration(true);//true表示加载配置文件，为false就是不加载配置文件，自己在代码里构建配置信息
//        fs是真正的客户端对象，FileSystem是抽象类，多态
        fs = FileSystem.get(conf);
//        fs = FileSystem.get(new URI("hdfs://192.168.206.6:2181"), conf, "hdfs");
        /***
         * 上述多态返回哪个子类呢？参考core-site.xml中的这段配置
         * 如果配置文件里有hdfs，就返回一个...
         * 同时会取windows里面配置得环境变量HADOOP_USER_NAME的值（我的电脑上自己没配置，但是有这个变量，不知道是啥时候自己配置上去的）
         *     <property>
         *         <!-- 为Hadoop 客户端配置默认的高可用路径  -->
         *         <name>fs.defaultFS</name>
         *         <value>hdfs://mycluster</value>
         *     </property>
         */
    }

    @Test
    public void mkdir() throws Exception {
        Path dir = new Path("/lxr");
        if (fs.exists(dir)){
//            true表示递归地删除
            fs.delete(dir,true);
        }
        fs.mkdirs(dir);
    }

    @After
    public void close() throws Exception {
        fs.close();
    }


}
