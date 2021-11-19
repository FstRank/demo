package com.itheima.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-15 11:57
 **/
public class PrivateLock1 {
    long start = System.currentTimeMillis();
    ReentrantLock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        PrivateLock1 lock1 = new PrivateLock1();
        
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock1.read();
            }).start();
        }
    }
    
    private void read() {
        lock.lock();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
