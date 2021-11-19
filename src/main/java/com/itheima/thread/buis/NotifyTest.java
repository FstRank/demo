package com.itheima.thread.buis;

public class NotifyTest {
    public static void main(String[] args) throws InterruptedException {
        
        byte[] lock = new byte[0];
        
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("t1 started");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        });
        
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("t2 started");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                    System.out.println("t3 notify");
                    lock.notifyAll();
                    // lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        t2.setPriority(3);
        t1.setPriority(1);
        t3.setPriority(2);
        
        t1.start();
        t2.start();
        Thread.sleep(100);
        t3.start();
        
    }
    
}
