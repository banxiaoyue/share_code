package com.mine.multiThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步synchronized
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        //有问题的方法
//        ThreadA threadA = new ThreadA();
//        Thread taa = new Thread(threadA);
//        Thread tab = new Thread(threadA);
//        taa.start();
//        tab.start();

        //使用synchronized 解决
        ThreadB threadB = new ThreadB();
        Thread tba = new Thread(threadB);
        Thread tbb = new Thread(threadB);
        tba.start();
        Thread.yield();
        tbb.start();
    }
}

/************************************* synchronized ********************************************/

/**
 * 对一个数字100进行减法直到数字为0
 * 有问题的方法
 */
class ThreadA implements Runnable {
    int a = 100;
    public void run() {
        while (a > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "---------------" + a--);
        }
    }
}

class ThreadB implements Runnable {
    int a = 100;//1,2使用
    Lock l = new ReentrantLock();
    Condition condition = l.newCondition();
    public void run() {
        l.lock();
        condition.signalAll();
        int b = a++;
        l.unlock();
    }

}

/************************************* synchronized ********************************************/


//
//l.lock();
//        try {
//        // access the resource protected by this lock
//        } finally {
//        l.unlock();
//        }