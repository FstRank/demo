package com.thread.create;

/**
 *  创建方式二：实现Runnable接口，重写run()方法，执行线程需要丢入runnable接口实现类，调用start方法
 */
public class MyThreadTwo implements Runnable {
    @Override
    public void run() {
        //run()方法线程体
        for (int i = 0; i < 10; i++) {
            System.out.println("线程创建二：" + i);
        }
    }

    public static void main(String[] args) {
        MyThreadTwo myThreadTwo = new MyThreadTwo();
        Thread thread = new Thread(myThreadTwo);
        thread.start();

        for (int i=0;i<10;i++){
            System.out.println("主线程："+i);
        }
    }
}
