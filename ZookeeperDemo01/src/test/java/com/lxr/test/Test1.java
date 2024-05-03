package com.lxr.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Test1 {
    //    这里也可以只写一个地址，表示连接写的那一个地址，写三个地址时，如果其中前面的地址没有连接上，就会自动连接后面的地址
    private String connectString = "192.168.206.6:2181,192.168.206.7:2181,192.168.206.8:2181";
    private int sessionTimeOut = 5000;
    ZooKeeper zooKeeper = null;

    /***
     * 连接Zookeeper客户端
     * @param args
     */
    @Before
    public void connect() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            /***
             * 触发监听事件的回调方法
             * @param watchedEvent
             */
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了。。。");
            }
        });
    }


    @Test
    public void createNode() throws Exception {
        String path = zooKeeper.create("/apptestIDEA1" // 节点路径
                , "HelloZookeeper".getBytes() // 节点的数据
                , ZooDefs.Ids.OPEN_ACL_UNSAFE // 权限
                , CreateMode.PERSISTENT // 节点类型
        );

        System.out.println(path);

    }

    /***
     * 判断节点是否存在
     * @throws InterruptedException
     * @throws KeeperException
     */

    @Test
    public void exists() throws InterruptedException, KeeperException {
//        true表示使用Zookeeper中的watch；返回节点的属性信息
        Stat stat = zooKeeper.exists("/apptest", true);
        if (stat != null) {
            System.out.println("节点存在,数据长度：" + stat.getDataLength());
            System.out.println("子节点数量：" + stat.getNumChildren());
        } else {
            System.out.println("节点不存在。");
        }
    }

    /***
     * 获取子节点
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        List<String> children = zooKeeper.getChildren("/app1", true);
        for (String child : children) {
            byte[] data = zooKeeper.getData("/app1/"+child, false, null);
//            注意这里要保证节点非空，否则new String(data)会报空指针错
            System.out.println(child+":" + new String(data));
        }
    }

    /***
     * 修改节点内容
     */
    @Test
    public void setData() throws InterruptedException, KeeperException {
//        -1就是不指定版本，自动维护；返回结果是节点属性
        Stat stat = zooKeeper.setData("/app1/modi", "666666666".getBytes(), -1);
        System.out.println(stat.getDataLength());
    }

    @Test
    public void deleteNode() throws InterruptedException, KeeperException {
        zooKeeper.delete("/app1/app0000000002",-1);
    }

    /***
     * 监听子节点(添加/删除子节点)变化，子节点内容变化不会触发
     */
    @Test
    public void NodeChildrenChanged() throws InterruptedException, KeeperException {
//        NodeChildrenChanged的方法是getChildren
        List<String> children = zooKeeper.getChildren("/app1", new Watcher() {
//            点击getType(),然后点击public Watcher.Event.EventType getType()中的EventType可以查看各种type的枚举
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("------->"+watchedEvent.getType());
            }
        });

        for (String child : children) {
            System.out.println(child);
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    /***
     *  该节点内容变化时触发
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void NodeDataChanged() throws InterruptedException, KeeperException {
//        NodeChildrenChanged的方法是getData
        byte[] data = zooKeeper.getData("/app1/watch", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("--->" + watchedEvent.getType());
            }
        }, null);

        System.out.println("--->" + new String(data));
        Thread.sleep(Integer.MAX_VALUE);
    }
}
