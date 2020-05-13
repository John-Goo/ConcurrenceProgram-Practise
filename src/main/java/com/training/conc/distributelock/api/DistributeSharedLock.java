package com.training.conc.distributelock.api;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

public class DistributeSharedLock extends AbstractDistributeLock {
    InterProcessMutex sharedLock = null;
    public DistributeSharedLock(String lockPath) {
        super(lockPath);
    }

    @Override
    public void tryLock() throws Exception {
        sharedLock = new InterProcessMutex(client, lockPath);
        sharedLock.acquire();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        sharedLock = new InterProcessMutex(client, lockPath);
        boolean status =  sharedLock.acquire(time,unit);
        return status;
    }

    @Override
    public void release() {
        if(sharedLock !=null){
            try {
                sharedLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(client !=null){
            client.close();
        }
    }
}
