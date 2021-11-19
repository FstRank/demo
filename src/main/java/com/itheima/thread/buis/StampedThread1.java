package com.itheima.thread.buis;

import java.util.concurrent.locks.StampedLock;

public class StampedThread1 {
    StampedLock lock = new StampedLock();
    
    void write() {
        long l = lock.writeLock();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(l);
        }
    }
    
    void read() {
        long stamp = lock.tryOptimisticRead();
        if (lock.validate(stamp)) {
            System.out.println("read");
        }
    }
    
    public static void main(String[] args) {
        StampedThread1 hungryThread = new StampedThread1();
        
        Thread t1 = new Thread(() -> {
            //不停去拿写锁，拿到后sleep一段时间，释放
            while (true) {
                hungryThread.write();
            }
        });
        
        Thread t2 = new Thread(() -> {
            //不停去拿读锁，虽然是读锁，但是...看下面！
            while (true) {
                hungryThread.read();
            }
        });
        
        t1.setPriority(9);
        //优先级低！
        t2.setPriority(1);
        
        t1.start();
        t2.start();
    }
}
