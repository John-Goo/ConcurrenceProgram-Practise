package com.training.conc.distributelock.api;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.framework.recipes.shared.SharedCount;

import java.util.concurrent.TimeUnit;

public class DistributeSemaphoreLock extends AbstractDistributeLock{

    final static String _SEMAPHORE = "/shared-semaphore";

    private  Integer seedValue;

    InterProcessSemaphoreV2 semaphoreV2 = null;
    Lease lease =null;

    public DistributeSemaphoreLock(String lockPath,Integer seedV) {
        super(lockPath);
        this.seedValue = seedV;
    }

    @Override
    public void tryLock() throws Exception {
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws Exception {
        semaphoreV2 = new InterProcessSemaphoreV2(client, _SEMAPHORE,
                new SharedCount(client, lockPath, seedValue));
        Lease lease = semaphoreV2.acquire(20, TimeUnit.SECONDS);
        return lease==null?false:true;
    }

    @Override
    public void release() {
        if(semaphoreV2 != null && lease != null) {
            semaphoreV2.returnLease(lease);
        }
        if(client !=null) {
            client.close();
        }


    }
}
