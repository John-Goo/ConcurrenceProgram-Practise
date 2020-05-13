package com.training.conc.distributelock;

import com.training.conc.distributelock.api.DistributeReadLock;
import com.training.conc.distributelock.api.DistributeSemaphoreLock;
import com.training.conc.distributelock.api.DistributeSharedLock;
import com.training.conc.distributelock.api.DistributeWriteLock;
import com.training.conc.util.CuratorUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCount;

import java.util.concurrent.TimeUnit;

public class CuratorDistributeLockDemo {
    // 父目录
    static final String _NAMESPACE = "distribute-lock";
    //独占锁的共享目录
    static final String _SHAREDLOCK = "sharedlock";
    static final String _Write_Path = "testlock1";
    static final String _Read_Path = "testlock2";




    /**
     * 分布式排斥锁或独占锁
     */
    public void execSharedLock() {
        DistributeSharedLock lock = new DistributeSharedLock("/sharedlock");
        try {
            System.out.println("线程ID:" + Thread.currentThread().getId() + ",申请进入锁——");
            ///////////////////////////////////////////////////////////
            if (lock.tryLock(60,TimeUnit.SECONDS)) {
                System.out.println("线程ID:" + Thread.currentThread().getId() + ",已获得锁，正在执行...");
                Thread.sleep(2 * 1000);
                System.out.println("线程ID:" + Thread.currentThread().getId() + ",执行完结，即将释放锁.");
            } else {
                System.out.println("线程ID：" + Thread.currentThread().getId() + " 等待10s 未获得**锁,结束等待.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }
    }

    /**
     * 执行读锁操作
     */
    public void execReadLock() {
        DistributeReadLock readLock = new DistributeReadLock("/readlock");
        try {
            System.out.println("线程ID：" + Thread.currentThread().getId() + " 正在申请**读锁,等待中...");
            if (readLock.tryLock(60,TimeUnit.SECONDS)) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("线程ID：" + Thread.currentThread().getId() + " 获得**读锁,正在执行.序号==>" + i);
                    Thread.sleep(2000);
                }
            } else {
                System.out.println("线程ID：" + Thread.currentThread().getId() + " 等待10s 未获得**读锁,结束等待.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.release();

        }

    }

    /**
     * 执行写锁操作
     */
    public void execWriteLock() {
        DistributeWriteLock writeLock  = new DistributeWriteLock("/writelock");
        try {
            System.out.println("线程ID：" + Thread.currentThread().getId() + " 正在申请**写锁,等待中...");
            if (writeLock.tryLock(60,TimeUnit.SECONDS)) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程ID：" + Thread.currentThread().getId() + " 获得**写锁,正在执行.序号==>" + i);
                    Thread.sleep(2*1000);
                }
            } else {
                System.out.println("线程ID：" + Thread.currentThread().getId() + " 等待10s 未获得**写锁,结束等待.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.release();
        }
    }

    /**
     * 信号量锁
     */
    public void execSemaphoreLock() {
        DistributeSemaphoreLock lock = new DistributeSemaphoreLock("/semaphore",3);
        try {
            System.out.println("线程ID:" + Thread.currentThread().getId() + "等待申请锁***" );
          ///////////////////////////////////////////////////////////////
           if(lock.tryLock(60,TimeUnit.SECONDS)) {
               for (int i = 0; i < 5; i++) {
                   System.out.println("线程ID:" + Thread.currentThread().getId() + "正在执行，序号：" + i);
                   Thread.sleep(1000);
               }
           }else{
               System.out.println("线程ID:" + Thread.currentThread().getId() + "未获得锁，等待超时！");
           }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            System.out.println("线程ID:" + Thread.currentThread().getId() + "已释放锁." );
            lock.release();
            //////////////////////////////////////////////////////////////////////
        }
    }

}
