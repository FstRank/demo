package com.itheima.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-15 11:57
 **/
public class ShareLock1 {
    long start = System.currentTimeMillis();
    // ReentrantLock lock = new ReentrantLock();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public static void main(String[] args) {
        ShareLock1 lock1 = new ShareLock1();
        
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock1.read();
            }).start();
        }
    }
    
    private void read() {
        ReentrantReadWriteLock.WriteLock readLock = lock.writeLock();
        readLock.lock();
        // lock.lock();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // lock.unlock();
            readLock.unlock();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
