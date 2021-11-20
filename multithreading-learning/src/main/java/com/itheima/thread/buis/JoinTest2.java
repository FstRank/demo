package com.itheima.thread.buis;

public class JoinTest2 implements Runnable {
    
    byte[] lock = new byte[0];
    
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("I am sub");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("main.start");
        JoinTest2 test = new JoinTest2();
        Thread sub = new Thread(test); //NEW
        synchronized (test.lock) {
            //1.为什么不会死锁？
            sub.start();  //RUNNABLE
            //2.打开join试试
            // sub.join();
            
            // synchronized (test.lock) {
            //     System.out.println("I am sub");
            // }
        }
        
        System.out.println("main.end");
    }
}
