package com.training.conc.distributelock.api;

import com.training.conc.util.CuratorUtil;
import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.TimeUnit;

public abstract class AbstractDistributeLock {
    protected String lockPath;

    protected CuratorFramework client ;


    final static String _NAMESPACE= "distribute-lock";

    public AbstractDistributeLock(String lockPath) {
        this.lockPath = lockPath;
        client = CuratorUtil.newClient(_NAMESPACE);
    }



    /**
     * 开启锁方法
     */
    public abstract void tryLock() throws Exception;

    /**
     * 开启分布式锁方法
     * @param time
     * @param unit
     * @return
     */
    public abstract boolean tryLock(long time, TimeUnit unit) throws Exception;

    /**
     *  释放锁
     */
    public abstract void release();

}
