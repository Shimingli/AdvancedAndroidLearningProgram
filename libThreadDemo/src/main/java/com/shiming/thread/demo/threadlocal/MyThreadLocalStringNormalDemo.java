package com.shiming.thread.demo.threadlocal;

import java.util.concurrent.CountDownLatch;

/**
 * @author : ShiMing
 * @editor : 我还是不太明白
 * @description :
 * @created : 2020-05-13 19:53
 */
public class MyThreadLocalStringNormalDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String getString() {

        return threadLocal.get();
    }

    private void setString(String string) {
        threadLocal.set(string);
    }

    public static void main(String[] args) {
        int threads = 1000;
        MyThreadLocalStringNormalDemo demo = new MyThreadLocalStringNormalDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(() -> {
                demo.setString(Thread.currentThread().getName());
                System.out.println(demo.getString());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();

//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    demo.setString(Thread.currentThread().getName());
//                    System.out.println("************ start **************** "+demo);
//                    if (demo.getString().contains(finalI+"")) {
//                        System.out.println(demo.getString() + "  -------- " + finalI+ "我是包含了的呀 ------------");
//                    }else {
//                        System.out.println(demo.getString()+"  &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   "+ finalI);
//                    }
//                    countDownLatch.countDown();
//                    System.out.println("************ end ****************");
//                }
//            }).start();

        }
    }


}
