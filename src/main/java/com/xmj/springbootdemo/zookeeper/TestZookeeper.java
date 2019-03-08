package com.xmj.springbootdemo.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/3/1 23:57
 */
public class TestZookeeper {

    //连接地址
    private static final String CONNECTSTRING = "127.0.0.1:2181";
    //zk Session超时时间
    private static final int SESSION_OUTTIM = 2000;
    //使用 CountDownLatch 阻塞用户程序，用户必须等待连接，发送成功信号
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zk = new ZooKeeper(CONNECTSTRING, SESSION_OUTTIM, new Watcher() {
            //子线程
            @Override
            public void process(WatchedEvent watchedEvent) {
                //1.获取事件状态
                Event.KeeperState keeperState = watchedEvent.getState();
                //2.判断为连接状态
                if (Event.KeeperState.SyncConnected == keeperState){
                    //获取事件类型
                    Event.EventType eventType = watchedEvent.getType();
                    if (eventType == Event.EventType.None){
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {

                        }
                        COUNT_DOWN_LATCH.countDown();
                        System.out.println("zookeeper开始启动连接........");
                    }
                }

            }
        });
        //阻塞
        COUNT_DOWN_LATCH.await();
        //创建为持久类型 ，节点开放权限   、主线程
        String result = zk.create("/test-xmj","886".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println("新增节点信息："+result);
        zk.close();
    }

}
