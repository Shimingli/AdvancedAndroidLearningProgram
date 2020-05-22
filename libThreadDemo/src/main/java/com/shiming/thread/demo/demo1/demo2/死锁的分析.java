package com.shiming.thread.demo.demo1.demo2;

public class 死锁的分析 {

    static boolean flag;
    static int i,j;

    public static void test() {
        if (flag) {

            while (true) {
                // 第一步：CPU随机性切换到：Thread-1   Thread-1正常往下走......
                // 第六步：CPU随机性切换到：Thread-1 ，然后判断同步锁 注意：此时的同步锁🔒Lock == 第二把锁🔒， 第二把锁🔒是被Thread-1锁定的，就造成了线程之间不和谐：死锁

                synchronized (Lock.LOCK1) // 使用第一把锁🔒
                {

                    synchronized (Lock.LOCK2) // 使用第二把锁🔒
                    {
                        System.out.println("一一一一一一一一一一一一" + i++);
                        // 第二步：CPU随机性切换到：Thread-1  就在此时，CPU执行权没有了，  CPU去执行其他线程了
                    }
                }
                // 第四步：   CPU随机性切换到：Thread-1   Thread-1正常往下走完，并解锁🔓
            }

        } else {

            while(true) {
                // 第三步：CPU随机切换到：Thread-0，然后判断同步锁 注意：此时的同步锁🔒Lock == 第二把锁🔒， 第二把锁🔒是被Thread-1锁定的，就造成了线程之间不和谐：死锁

                synchronized (Lock.LOCK2) // 使用第二把锁🔒
                {

                    synchronized (Lock.LOCK1) // 使用第一把锁🔒
                    {

                        // 第五步：CPU随机性切换到：Thread-0 就在此时，CPU执行权没有了，  CPU去执行其他线程了
                        System.out.println("二二二二二二二二二二二二" + j++);
                    }

                }

            }

        }
    }

}
