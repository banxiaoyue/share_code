package com.mine.multiThread;

/**
 * 线程实现
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        //方式一
//        ThreadOne thread = new ThreadOne();
//        thread.start();
        //方式二
        ThreadTwo threadTwo = new ThreadTwo();
        Thread t1 = new Thread(threadTwo);
        t1.start();
    }
}

class ThreadOne extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---------继承Thread类----------" + Thread.currentThread().toString());
    }
}

class ThreadTwo implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---------继承Runnable类----------" + Thread.currentThread().toString());
    }
}


