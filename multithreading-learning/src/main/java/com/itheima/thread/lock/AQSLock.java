package com.itheima.thread.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-15 17:12
 **/
public class AQSLock extends AbstractQueuedSynchronizer {
    public AQSLock(int i) {
        super.setState(i);
    }
    
    @Override
    protected boolean tryReleaseShared(int arg) {
        for (; ; ) {
            int state = super.getState();
            int newState = state + arg;
            if (super.compareAndSetState(state, newState)) {
                return true;
            }
        }
    }
    
    @Override
    protected int tryAcquireShared(int arg) {
        for (; ; ) {
            int curr = super.getState();
            int newState = curr - arg;
            if (newState < 0 || super.compareAndSetState(curr, newState)) {
                return newState;
            }
        }
    }
    
    public static void main(String[] args) {
        AQSLock lock = new AQSLock(9);
        for (int i = 0; i < 15; i++) {
            final int k = i;
            new Thread(() -> {
                lock.acquireShared(3);
                try {
                    Thread.sleep(1000);
                    System.out.println("输出" + k);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.releaseShared(3);
                }
            }).start();
        }
    }
}
