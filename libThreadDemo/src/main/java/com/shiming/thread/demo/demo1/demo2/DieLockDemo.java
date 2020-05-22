package com.shiming.thread.demo.demo1.demo2;

/**
 * 定义死锁任务
 */
class DieLockThread extends Thread {

    /**
     * 此变量已经不是共享数据了，因为：
     *              DieLockThread extends Thread
     *              new DieLockThread().start();
     *              new DieLockThread().start();
     *
     * 所以：Thread-0有自己的flag     Thread-1也有自己的flag
     */
    private boolean flag;

    public DieLockThread(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        int i = 0;
        int j = 0;

        if (flag) {

            while (true) {

                synchronized (Lock.LOCK1) // 使用第一把锁🔒
                {

                    synchronized (Lock.LOCK2) // 使用第二把锁🔒
                    {
                        System.out.println("一一一一一一一一一一一一" + i++);
                    }
                }

            }

        } else {

            while(true) {

                synchronized (Lock.LOCK2) // 使用第二把锁🔒
                {

                    synchronized (Lock.LOCK1) // 使用第一把锁🔒
                    {
                        System.out.println("二二二二二二二二二二二二" + j++);
                    }

                }

            }

        }
    }
}

/**
 * 定义两把锁🔒
 */
class Lock {
    public final static Object LOCK1 = new Object();
    public final static Object LOCK2 = new Object();
}

public class DieLockDemo {

    public static void main(String[] args) {
        // 多线程
        new DieLockThread(true).start();
        new DieLockThread(false).start();
    }

}
