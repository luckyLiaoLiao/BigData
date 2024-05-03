package com.lxr.test;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Test2 {

    private String connectString = "192.168.206.6:2181,192.168.206.7:2181,192.168.206.8:2181";

    private int sessionTimeOut = 10000;

    ZooKeeper zooKeeper = null;

    /**
     * 连接Zookeeper服务端
     */
    @Before
    public void test1() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            /**
             * 触发监听事件的回调方法
             * @param watchedEvent
             */
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了.....");
            }
        });
        //System.out.println("--->" + zooKeeper);
    }

    /**
     * 创建节点
     */
    @Test
    public void createNode() throws Exception{
        String path = zooKeeper.create("/apptestIDEA1" // 节点路径
                ,"HelloZookeeper".getBytes() // 节点的数据
                , ZooDefs.Ids.OPEN_ACL_UNSAFE // 权限
                , CreateMode.PERSISTENT // 节点类型
        );
        System.out.println(path);
    }

}
