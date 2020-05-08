package com.shiming.thread.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author : ShiMing
 * @editor :
 * @description : look lib
 * @created : 2020-05-08 08:47
 */
public class ThreadTestDemo {

    static class ThreadDemo1 implements Runnable{

        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(10);
                    System.out.println("ThreadDemo1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadDemo2 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(10);
                    System.out.println("ThreadDemo2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadDemo3 implements Callable<String>{

        @Override
        public String call() throws Exception {
            // TODO: 2020/5/8 需要注意一下这个处理的方法  
            boolean interrupted = Thread.currentThread().isInterrupted();
            System.out.println("interrupted "+interrupted);
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(10);
                    System.out.println("ThreadDemo3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "shiming";
        }
    }

    public static void main(String[] args){
        System.out.println("start");
        
        
        
        
        

        ThreadDemo2 threadDemo2 = new ThreadDemo2();

        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        Thread thread = new Thread(threadDemo1);

        ThreadDemo3 threadDemo3 = new ThreadDemo3();
        FutureTask<String> stringFutureTask = new FutureTask<>(threadDemo3);
        Thread thread3 = new Thread(stringFutureTask);


        thread3.start();
        try {
            thread3.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.interrupt();


        try {
            //jion()的调用在start的后面 可以保证thread 的完全的输出
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        try {
            //jion()的调用在start的后面 可以保证thread 的完全的输出
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadDemo2.start();



    }
}
