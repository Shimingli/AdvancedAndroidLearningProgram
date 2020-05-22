package com.shiming.thread.demo.demo1.demo1;

/**
 * synchronized 内置🔒
 *
 * 类说明：演示对象锁和类锁
 */
public class SynClzAndInst {

    // TODO =================================== 下面是类锁

    // 使用类锁的线程
    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            synClass();
        }
    }

    // 类锁，实际是锁类的class对象
    // synchronized == 类锁 == SynClzAndInst.class的对象锁🔒
    private static synchronized void synClass() {
        SleepTools.second(1);
        System.out.println("synClass going...");
        SleepTools.second(1);
        System.out.println("synClass end");
    }

    // 特殊的类锁 obj对象类锁  只有一份
    private static Object obj = new Object();

    private void synStaticObject() {
        synchronized (obj) {//类似于类锁，Obj在全虚拟机只有一份
            SleepTools.second(1);
            System.out.println("synClass going...");
            SleepTools.second(1);
            System.out.println("synClass end");
        }
    }




    // TODO =================================== 下面是对象锁

    // 使用对象锁
    private static class SynObject implements Runnable {
        private SynClzAndInst synClzAndInst;

        public SynObject(SynClzAndInst synClzAndInst) {
            this.synClzAndInst = synClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..." + synClzAndInst);
            synClzAndInst.instance();
        }
    }

    // 使用对象锁
    private static class SynObject2 implements Runnable {
        private SynClzAndInst synClzAndInst;

        public SynObject2(SynClzAndInst synClzAndInst) {
            this.synClzAndInst = synClzAndInst;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running..." + synClzAndInst);
            synClzAndInst.instance2();
        }
    }

    // 锁对象
    private synchronized void instance() {
        SleepTools.second(3);
        System.out.println("synInstance is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance ended " + this.toString());
    }

    // 锁对象
    private synchronized void instance2() {
        SleepTools.second(3);
        System.out.println("synInstance2 is going..." + this.toString());
        SleepTools.second(3);
        System.out.println("synInstance2 ended " + this.toString());
    }


    // TODO =================================== 下面是测试方法

    public static void main(String[] args) {

        SynClzAndInst synClzAndInst = new SynClzAndInst();
        Thread t1 = new Thread(new SynObject(synClzAndInst));

        SynClzAndInst synClzAndInst2 = new SynClzAndInst();
        Thread t2 = new Thread(new SynObject2(synClzAndInst));

        t1.start();
        t2.start();

        SynClass synClass = new SynClass();
        synClass.start();
        SleepTools.second(1);
    }
}
