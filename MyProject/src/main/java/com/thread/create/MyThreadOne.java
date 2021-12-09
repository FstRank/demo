package com.thread.create;

/**
 *  创建线程方式一：继承Thread类，重写run()方法，调用start开启线程
 */
public class MyThreadOne extends Thread{
    @Override
    public void run(){
        //run()方法线程体
        for (int i = 0; i < 10; i++) {
            System.out.println("线程创建一：" + i);
        }
    }

    public static void main(String[] args) {
        MyThreadOne myThread = new MyThreadOne();
        myThread.start();

        for (int i=0;i<10;i++){
            System.out.println("主线程："+i);
        }
    }
}
