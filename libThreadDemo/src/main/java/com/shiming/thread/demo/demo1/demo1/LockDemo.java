package com.shiming.thread.demo.demo1.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示锁的范式
 */
public class LockDemo {

    private int count = 0;

    // 内置锁 == this
    private synchronized void test() {

    }

    // 内置锁 == LockDemo.class
    private static synchronized void test2() {

    }

    private synchronized void test3() {
        // 业务逻辑，无法被中断
    }


    // 声明一个显示锁之可重入锁  new 可重入锁
    // 非公平锁
    private Lock lock = new ReentrantLock();

    public void incr() {
        // 使用 显示锁 的规范
        lock.lock();
        try {
            count++;
        } finally {   // 打死都要执行  最后一定会执行
            lock.unlock();
        }
    }

    // 可重入锁🔒  意思就是递归调用自己，锁可以释放出来
    // synchronized == 天生就是 可重入锁🔒
    // 如果是非重入锁🔒 ，就会自己把自己锁死
    public synchronized void incr2() {
        count++;
        incr2();
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();

        lockDemo.incr2();
    }

}
