package com.training.conc.distributelock;


import com.training.conc.util.CuratorUtil;
import org.apache.curator.framework.CuratorFramework;

/**
 * 演示高级 zookeeper api curator
 */
public class CuratorApiDemo {

    public static void creatDir(){
        CuratorFramework client = CuratorUtil.newClient("curator_root");
        try {
            client.create().forPath("/d1","hello,world!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
    }

    public static void fetchDirData(){
        CuratorFramework client = CuratorUtil.newClient("curator_root");
        try {
            byte[] data = client.getData().forPath("/d1");
            System.out.println("查询结果数据："+new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
    }


    public static void modifyDirData(){
        CuratorFramework client = CuratorUtil.newClient("curator_root");
        try {
            client.setData().forPath("/d1","John Goo".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
    }
    public static void deleteDir(){
        CuratorFramework client = CuratorUtil.newClient("curator_root");
        try {
            client.delete().forPath("/d1");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
    }
    public static void main(String[] args) {
        /*创建目录及初始化数据*/
       // creatDir();
      //  modifyDirData();
        /* 删除目录*/
        deleteDir();
        /*查询目录下的数据*/
        fetchDirData();
    }
}
