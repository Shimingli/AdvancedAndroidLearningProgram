package com.shiming.thread.demo.demo1.demo5;

// 给每个线程进行了隔离， 相当于 给每一个线程 Copy了一份副本一样
public class TestThreadLocal {

    static ThreadLocal<String> threadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "雄霸";
        }
    };

    private static class StudentThread extends Thread {

        @Override
        public void run() {
            super.run();

            // 首次拿 雄霸
            // 非常明确 1.获取当前是那个线程   2.ThreadLocalMap
            threadLocal.get(); // 雄霸

            String threadName = currentThread().getName();

            threadLocal.set("步惊云");

            // get 永远都是再 StudentThread 线程修改的的
            System.out.println("threadName:" + threadName + " get:" + threadLocal.get()); // 步惊云
        }
    }

    private static class PersonThread extends Thread {

        @Override
        public void run() {
            super.run();

            String threadName = currentThread().getName();

            threadLocal.get(); // 雄霸

            threadLocal.set("秦霜");

            // get 永远都是再 PersonThread 线程修改的的
            System.out.println("threadName:" + threadName + " get:" + threadLocal.get()); // 秦霜
        }
    }

    private static class WorkderThread extends Thread {

        @Override
        public void run() {
            super.run();

            String threadName = currentThread().getName();

            System.out.println("threadName:" + threadName + " get:" + threadLocal.get()); // 雄霸
        }
    }

    public static void main(String[] args) {
        new StudentThread().start();
        new PersonThread().start();
        new WorkderThread().start();

        String threadName = Thread.currentThread().getName();
        System.out.println("threadName:" + threadName + " get:" + threadLocal.get());
    }
}
