package com.thread.test;

import org.junit.Test;

/**
 * 测试观察线程的不同状态
 */
public class TestState {
    //观察线程创建后，启动前，启动后的状态
    @Test
    public void test1() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------");
        });
        //获取线程启动前状态
        Thread.State state = thread.getState();
        System.out.println(state);

        //获取线程启动后状态
        thread.start();
        state = thread.getState();
        System.out.println(state);

        //持续输出线程状态
        while (state != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = thread.getState();
            System.out.println(state);
        }
    }

    //观察线程抢占资源阻塞
    //开启线程后主线成休眠1秒，线程1执行后立即休眠1秒，所以线程2先拿到锁，之后线程2休眠3秒
    //线程1结束休眠后获取不到锁进入阻塞状态
    @Test
    public void test2() {
        byte[] lock = new byte[0];
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
        //Blocked
        System.out.println(thread1.getState());
        //Timed_WAITING
        System.out.println(thread2.getState());
    }

    //观察线程waiting状态
    @Test
    public void test3() {
        byte[] lock = new byte[0];
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState());
    }
}
