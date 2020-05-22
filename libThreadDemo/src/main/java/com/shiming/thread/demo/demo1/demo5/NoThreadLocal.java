package com.shiming.thread.demo.demo1.demo5;

/**
 * 类说明：没有ThreadLocal的情况演示
 *
 * 数字会乱套 例如：341  134  124 等等
 */
public class NoThreadLocal {

    // 静态的
    static Integer count = new Integer(1);

    /**
     * 运行3个线程
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestTask(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start(); // 启动三个线程
        }
    }

    /**
     * 类说明：
     */
    public static class TestTask implements Runnable {
        int id;

        public TestTask(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            count = count + id; // 123  231
            System.out.println(Thread.currentThread().getName() + ":" + count);
        }
    }

    public static void main(String[] args) {
        NoThreadLocal test = new NoThreadLocal();
        test.StartThreadArray();

        // 每个线程没法独有自己的一份数据
    }
}
