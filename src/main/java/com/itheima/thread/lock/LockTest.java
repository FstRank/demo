package com.itheima.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    int i=0;

    public int read(){
        lock.readLock().lock();
        //。。。。
//        return i;
        lock.readLock().unlock();
        return i;
    }

    public int read2(){

        return i;
    }

    public void write(){
        lock.writeLock().lock();
        i++;
        //。。。

        i++;

        lock.writeLock().unlock();

    }
}
