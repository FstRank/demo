package com.itheima.thread;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        final byte[] lock = new byte[0];
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                }
            }
        });
        thread2.start();
        Thread.sleep(1000);
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        Thread.sleep(3000);
        System.out.println(thread1.getState());
    }
}
