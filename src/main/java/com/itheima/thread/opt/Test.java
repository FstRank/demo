package com.itheima.thread.opt;

public class Test {
    Integer i;
    Integer j;

    byte[] lock = new byte[0];
    byte[] lock2 = new byte[0];


    void f1(){
        synchronized (i){

            i++;
        }
    }

    void f2(){
        synchronized (j){

            j++;
        }
    }

    void f3(){
        synchronized (lock){
            for (int i1 = 0; i1 < 10; i1++) {
                //...
            }
        }

        for (int i1 = 0; i1 < 10; i1++) {
            synchronized (lock){
                //....
            }
        }
    }

    public static void main(String[] args) {

    }

}
