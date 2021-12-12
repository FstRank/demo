package com.thread.test;

/**
 * 通过sleep方法使线程休眠1秒钟
 */
public class TestSleep {
    public static void main(String[] args) {
        test1();
        test2();
    }

    //测试线程休眠
    private static void test1() {
        boolean flag = true;
        int count = 0;
        while (flag) {
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            if (count > 10) {
                flag = false;
            }
        }
    }

    //测试线程休眠并不会释放锁
    private static void test2() {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (thread1.getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 :" + thread1.getState());
            System.out.println("thread2 :" + thread2.getState());
        }

        //Timed_WAITING

    }

}
