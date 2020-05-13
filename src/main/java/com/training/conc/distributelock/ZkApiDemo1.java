package com.training.conc.distributelock;

import com.training.conc.util.ZKClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZkApiDemo1 {

    public static void main(String[] args) {
        /* 创建一个目录并初始化数据*/
        // createDir();
        /*删除目录*/
        deleteDir();
        /* 获取目录数据*/
        fetchDirData();


    }

    /**
     * 创建目录
     */
    public static void createDir(){
        ZooKeeper zk = ZKClient.Builder.newClient();
        try {
            zk.create("/zkapi","init".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            ZKClient.Builder.close();
        }

    }

    /**
     * 查询目录及数据
     */
    public static void fetchDirData(){
        ZooKeeper zk = ZKClient.Builder.newClient();
        try {
            // zk.create("/zkapi","init".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            byte[] data = zk.getData("/zkapi",false,null);
            System.out.println("result:"+new java.lang.String(data));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            ZKClient.Builder.close();
        }
    }

    /**
     * 删除目录及数据
     */
    public static void deleteDir(){
        ZooKeeper zk = ZKClient.Builder.newClient();
        try {
            //zk.create("/zkapi","init".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
           // -1 删除该目录下所有的数据
            zk.delete("/zkapi",-1);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            ZKClient.Builder.close();
        }

    }


}
