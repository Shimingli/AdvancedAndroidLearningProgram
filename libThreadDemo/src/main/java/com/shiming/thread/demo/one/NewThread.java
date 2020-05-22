package com.xiangxue.lib;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {

    // 第一种方式  实实在在的线程
    private static class StudentThread extends Thread {

        @Override
        public void run() {
            super.run();

            System.out.println("do work Thread");
        }
    }

    // 第二种方式  任务  ----》 Thread.start
    private static class PersonThread implements Runnable {

        @Override
        public void run() {
            System.out.println("do work Runnable");
        }
    }

    // 第三种方式，任务  return XXX  ----》 Thread.start
    private static class WorkerThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("do work WorkerThread");
            Thread.sleep(10000);
            return "run success";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        StudentThread thread = new StudentThread();
        thread.start(); // .start() 才能证明这个是线程
        // thread.run(); // TODO 这个和线程没有半毛钱关系，就是函数调用

        // 任务不能运行，需要寄托 Thread
        PersonThread personThread = new PersonThread();
        new Thread(personThread).start();

        // --- 有返回值  任务不能运行，需要寄托 Thread
        WorkerThread workerThread = new WorkerThread();
        FutureTask<String> futureTask = new FutureTask<>(workerThread);
        new Thread(futureTask).start();
        System.out.println(futureTask.get()); // 阻塞的
    }
}
