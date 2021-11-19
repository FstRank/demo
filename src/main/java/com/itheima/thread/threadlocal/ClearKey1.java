package com.itheima.thread.threadlocal;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-16 10:22
 **/
public class ClearKey1 {
    public static void main(String[] args) {
        ThreadLocal local = new ThreadLocal();
        local.set(100);
        System.out.println(local.get());
        System.gc();
        System.out.println(local.get());
        local = null;
        System.gc();
        Thread thread = Thread.currentThread();
        
    }
}
