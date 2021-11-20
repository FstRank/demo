package com.itheima.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedLock {
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock lock = readWriteLock.writeLock();
    long start = System.currentTimeMillis();
    void read() {
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("end time = "+(System.currentTimeMillis() - start));

    }

    public static void main(String[] args) {
        final SharedLock lock = new SharedLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    lock.read();
                }
            }).start();
        }
    }
}