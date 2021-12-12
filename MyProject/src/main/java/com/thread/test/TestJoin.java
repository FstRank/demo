package com.thread.test;

//主线程循环至10，强行执行创建的线程
public class TestJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("vip线程执行中");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 200; i++) {
            if (i == 10){
                thread.join();
            }
            System.out.println("main线程：" + i);
        }
    }
}
