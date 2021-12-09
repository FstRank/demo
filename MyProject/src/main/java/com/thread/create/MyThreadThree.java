package com.thread.create;

import java.util.concurrent.*;

public class MyThreadThree implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程创建三：" + i);
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadThree myThreadThree = new MyThreadThree();
        //创建服务
        ExecutorService sr = Executors.newSingleThreadExecutor();
        //提交线程执行
        Future<Boolean> f1 = sr.submit(myThreadThree);
        //关闭服务
        sr.shutdownNow();

        for (int i=0;i<10;i++){
            System.out.println("主线程："+i);
        }
        //打印返回结果
        boolean r1 = f1.get();
        System.out.println(r1);
    }
}
