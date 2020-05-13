package com.training.conc.distributelock.api;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

public class DistributeWriteLock extends AbstractDistributeLock {

    // 写锁
    private InterProcessMutex writelock = null;

    public DistributeWriteLock(String lockPath) {
        super(lockPath);
    }

    @Override
    public void tryLock() throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
        writelock = lock.writeLock();
        writelock.acquire();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
        writelock = lock.writeLock();
        return writelock.acquire(time, unit);
    }

    @Override
    public void release() {
        if (writelock != null && writelock.isAcquiredInThisProcess()) {
            try {
                writelock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (client != null) {
                client.close();
            }
        }
    }
}
