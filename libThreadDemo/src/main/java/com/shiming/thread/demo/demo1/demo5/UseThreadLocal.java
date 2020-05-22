package com.shiming.thread.demo.demo1.demo5;

/**
 * 说明：演示ThreadLocal的使用
 *
 * 数字不会乱套 例如：123  132  123 213 等等 始终在123范围中
 */
public class UseThreadLocal {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    /**
     * 运行3个线程
     */
    public void StartThreadArray() {
        Thread[] runs = new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i] = new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();
        }
    }

    /**
     * 类说明：测试线程，线程的工作是将ThreadLocal变量的值变化，并写回，
     * 看看线程之间是否会互相影响
     */
    public static class TestThread implements Runnable {
        int id;

        public TestThread(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + ":start");
            // 如果使用了 ThreadLocal 会单独Copy一份 到 当前线程 例如 Thread-0
            Integer s = threadLocal.get();
            s = s + id;
            threadLocal.set(s); // 这里所修改的内容 88 只对 Thread-0 又效果， 和 Thread-1 没有半毛钱关系
            System.out.println(Thread.currentThread().getName() + " :"
                    + threadLocal.get());
            //threadLocal.remove();
        }
    }

    public static void main(String[] args) {
        UseThreadLocal test = new UseThreadLocal();
        test.StartThreadArray();
    }
}
