package com.itheima.thread.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @program: src
 * @description:
 * @author: zhanghz001
 * @create: 2021-02-10 09:55
 **/
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
    //    自己实现一个锁，
    //    最大允许指定数量的线程并行运作。
    //    其他排队等候
    
    @Override
    protected int tryAcquireShared(int acquires) {
        for (; ; ) {
            int available = getState();
            int remaining = available - acquires;
            if (remaining < 0 ||
                    compareAndSetState(available, remaining))
                return remaining;
        }
    }
    
    @Override
    protected boolean tryReleaseShared(int releases) {
        for (; ; ) {
            int current = getState();
            int next = current + releases;
            if (next < current) // overflow
                throw new Error("Maximum permit count exceeded");
            if (compareAndSetState(current, next))
                return true;
        }
    }
    
    public MyAbstractQueuedSynchronizer(int count) {
        setState(count);
    }
    
    public static void main(String[] args) {
        MyAbstractQueuedSynchronizer aqs = new MyAbstractQueuedSynchronizer(10);
        
        for (int i = 0; i < 100; i++) {
            int b = i;
            new Thread(() -> {
                aqs.acquireShared(1);
                try {
                    System.out.println(b);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    aqs.releaseShared(1);
                }
            }).start();
        }
    }
}
