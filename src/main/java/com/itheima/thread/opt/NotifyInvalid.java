package com.itheima.thread.opt;

import java.util.concurrent.CopyOnWriteArrayList;

public class NotifyInvalid {
    CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    byte[] lock = new byte[0];
    
    public void del() {
        synchronized (lock) {
            System.out.println("线程名称 start: " + Thread.currentThread().getName());
            //没值就等，有值就删
            if (list.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程名称 end: " + Thread.currentThread().getName());
            list.remove(0);
        }
    }
    
    public void add() {
        synchronized (lock) {
            //加个值后唤醒
            list.add(0, 0);
            lock.notifyAll();
            // lock.notify();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        NotifyInvalid notifyInvalid = new NotifyInvalid();
        
        //启动两个线程等候删除
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                notifyInvalid.del();
            }).start();
        }
        Thread.sleep(100);
        //新线程添加一个
        new Thread(() -> {
            notifyInvalid.add();
        }).start();
        
        Thread.sleep(1000);
        
        System.out.println(notifyInvalid.list.size());
    }
    
}
