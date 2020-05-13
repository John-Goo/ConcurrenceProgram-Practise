package com.training.conc.util;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 *  ZooKeeper客户端实现
 */
public class ZKClient {
    static ThreadLocal<ZooKeeper> tl = new ThreadLocal<ZooKeeper>();
    // 连接服务端节点：ip:port,ip:port,...
    static String connectStr = "192.168.0.71:2181,192.168.0.72:2181,192.168.0.73:2181";
    // 会话失效时间
    private static final int _TIMEOUT = 10 * 1000;


    public static class Builder {

        /**
         * 获取zk 客户端
         *
         * @return
         */
        public static ZooKeeper newClient() {
            ZooKeeper zk = tl.get();
            try {
                if (zk == null) {
                    zk = new ZooKeeper(connectStr, _TIMEOUT, null);
                    System.out.println("线程ID："+Thread.currentThread().getId()+"创建 new ZooKeeper："+zk);
                    tl.set(zk);
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }
            System.out.println("线程ID："+Thread.currentThread().getId()+"获得客户端实例："+zk);
            return zk;
        }

        /**
         * 关闭zookeeper客户端
         */
        public static void close() {
            ZooKeeper zk = tl.get();
            if (zk != null) {
                try {
                    System.out.println("线程ID："+Thread.currentThread().getId()+"关闭zk："+zk);
                    zk.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程ID："+Thread.currentThread().getId()+"清空缓存："+zk);
            // 清空缓存
            tl.set(null);
        }
    }
}
