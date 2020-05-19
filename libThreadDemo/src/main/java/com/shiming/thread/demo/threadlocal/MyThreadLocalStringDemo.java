package com.shiming.thread.demo.threadlocal;

import java.util.concurrent.CountDownLatch;

/**
 * @author : ShiMing
 * @editor :  todo  研究研究 真的研究
 * @description :
 * @created : 2020-05-13 19:52
 */
public class MyThreadLocalStringDemo {
    private String string;

    private String getString() {
        return string;
    }

    private void setString(String string) {
        this.string = string;
    }

    public static void main(String[] args) {
        int threads = 1000;
        MyThreadLocalStringDemo demo = new MyThreadLocalStringDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        System.out.println("countDown  "  +threads);
        for (int i = 0; i < threads; i++) {

            Thread thread = new Thread(() -> {
                demo.setString(Thread.currentThread().getName());
                System.out.println(demo.getString());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();









//            System.out.println("shiming ---------------------------------------- =====  "  +i);
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    demo.setString(Thread.currentThread().getName());
//                    System.out.println("************ start ****************");
//                    if (demo.getString().contains(finalI+"")) {
//                        System.out.println(demo.getString() + "  -------- " + finalI+ "我是包含了的呀 ------------");
//                    }else {
//                        //很多时候 我们都会 输出到这里来  ？  why  ------
//                        //  因为当前的线程的name 已经错过了，就是没有执行的意思，所以这个演示了很正常的角色问题
//                        System.out.println(demo.getString()+"  &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+ finalI);
//                    }
//                    countDownLatch.countDown();
//                    System.out.println("************ end ****************");
//                }
//            }).start();

        }

    }


}