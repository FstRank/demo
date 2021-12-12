package com.thread.test;

public class ThreadCommunicationTest {
    private volatile int count =0;
    public static void main(String[] args) {
        Object obj = new Object();
        ThreadCommunicationTest test=new ThreadCommunicationTest();
        //test1(test);
        test2(test,obj);
    }

    private static void test1(ThreadCommunicationTest test) {
        Thread thread1 = new Thread(()->{
            try {
                while (true) {
                    test.add();
                    Thread.sleep(1000);
                    if (test.count == 10) {
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread thread2 = new Thread(()->{
            try {
                while (true) {
                    if (test.count == 5) {
                        System.out.println("count新增到5，线程2退出");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }

    private static void test2(ThreadCommunicationTest test, Object obj) {
        Thread thread1 = new Thread(()->{
            try {
                synchronized (obj){
                    while (true) {
                        test.add();
                        Thread.sleep(1000);
                        if (test.count==5){
                            System.out.println("count增加到5，线程2结束");
                            obj.wait();
                        }
                        if (test.count == 10) {
                            throw new InterruptedException();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread thread2 = new Thread(()->{
            try {
                Thread.sleep(2000);
                synchronized (obj){
                    obj.notify();
                    throw new InterruptedException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }



    private void add(){
        count++;
        System.out.println(count);
    }

}
