package com.training.conc.distributelock.api;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

public class DistributeReadLock extends AbstractDistributeLock {
    // 读锁
    private InterProcessMutex readlock=null;

    public DistributeReadLock(String lockPath) {
        super(lockPath);
    }

    @Override
    public void tryLock() throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
        readlock = lock.readLock();
        readlock.acquire();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
        readlock = lock.readLock();
        return readlock.acquire( time, unit);
    }

    @Override
    public void release() {
        if(readlock !=null && readlock.isAcquiredInThisProcess()){
            try {
                readlock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(client !=null){
            client.close();
        }

    }
}
