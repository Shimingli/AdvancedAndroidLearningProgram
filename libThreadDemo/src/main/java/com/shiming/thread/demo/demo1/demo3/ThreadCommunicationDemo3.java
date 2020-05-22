package com.shiming.thread.demo.demo1.demo3;

/**
 * 描述资源
 */
class Res3 {

    /**
     * name 是共享数据，被Thread-0 Thread-1公用使用
     */
    private String name;

    /**
     * id 是共享数据，被Thread-0 Thread-1公用使用
     */
    private int id;

    /**
     * flag 是共享数据，被Thread-0 Thread-1公用使用
     */
    private boolean flag; // 定义标记 默认第一次为false

    /**
     * 对操作共享数据的地方加入同步锁的方式来解决安全问题
     * public synchronized(this) void put(String name) {
     */
    public synchronized void put(String name) {

        /**
         * 生产之前判断标记
         */
        if (!flag) {

            // 开始生产
            id += 1;
            // this.name = name + " 商品编号:" + id;
            System.out.println(Thread.currentThread().getName() + "生产者 生产了：" + this.id);
            // 生产完毕

            /**
             * 修改标记
             */
            flag = true;

            /**
             * 唤醒 wait(); 冻结的线程，如果没有就是空唤醒，Java是支持的
             * 生产好了，消费者 快来买 ，唤醒
             */
            notify(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着

            /**
             * 当前自己线程 冻结，释放CPU执行资格，释放CPU执行权，CPU就会去执行其他线程了
             */
            try {
                // 生产好一个，我就去睡觉了
                wait(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对操作共享数据的地方加入同步锁的方式来解决安全问题
     * public synchronized(this) void put(String name) {
     */
    public synchronized void out() {

        /**
         * 消费之前判断标记
         */
        if (flag) {

            // 开始消费
            System.out.println(Thread.currentThread().getName() +  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> 消费者 消费了：" + this.id);
            // 消费完毕

            /**
             * 修改标记
             */
            flag = false;

            /**
             * 唤醒 wait(); 冻结的线程，如果没有就是空唤醒，Java是支持的
             * 唤醒生产者，你可以再生产一个面包了
             */
            notify(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着

            /**
             * 当前自己线程 冻结，释放CPU执行资格，释放CPU执行权，CPU就会去执行其他线程了
             */
            try {
                // 顾客又睡觉了
                wait(); // 注意：⚠️ wait();  notify();  这些必须要有同步锁包裹着
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 描述生产者任务
 */
class ProduceRunnable3 implements Runnable {

    /**
     * 此变量已经不是共享数据了，因为：
     *              new Thread(produceRunnable).start();
     *              new Thread(consumeRunnable).start();
     *
     * 所以：Thread-0有自己的res     Thread-1也有自己的res
     */
    private Res3 res;

    ProduceRunnable3(Res3 res) {
        this.res = res;
    }

    /**
     * 执行线程任务
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            res.put("面包🍞");
        }
    }
}

/**
 * 描述消费者任务
 */
class ConsumeRunnable3 implements Runnable {

    /**
     * 此变量已经不是共享数据了，因为：
     *              new Thread(produceRunnable).start();
     *              new Thread(consumeRunnable).start();
     *
     * 所以：Thread-0有自己的res     Thread-1也有自己的res
     */
    private Res3 res;

    ConsumeRunnable3(Res3 res) {
        this.res = res;
    }

    /**
     * 执行线程任务
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            res.out();
        }
    }
}

/**
 * 以上案例二，虽然解决了安全🔐问题，但是：CPU随机执行线程，搞得很混乱，没有满足（生产一个，消费一个）的功能
 *
 *
 * 1.定义共享数据 boolean flag = false;
 * 2.生产者 if(flag == false) { 开始生产  生产完毕后 notify唤醒，由于第一次没有线程wait()等待， 属于空唤醒，在Java里面是运行空唤醒的 }
 * 3.生产者，设置为冻结状态：释放CPU执行资格，释放CPU执行权 ，CPU就可以去执行Thread-0线程了
 * 4.消费者 if(flag == false) { 开始消费 消费完毕后 notify唤醒(注意：⚠️如果不notify唤醒 就属于死锁了,因为两个线程都冻结了)，然后在wait(); 冻结状态：释放CPU执行资格，释放CPU执行权 ,CPU就会去执行Thread-1线程了}
 * 这样来回的切换，生产者生产后，告诉消费者，消费者消费后，告诉生产者 ............
 *
 *
 * 多线程通讯案例
 *
 *
 * 案例三：等待唤醒机制：
 * 　　 wait(); 等待/冻结 ：可以将线程冻结，释放CPU执行资格，释放CPU执行权，并把此线程临时存储到线程池
 *  　　notify(); 唤醒线程池里面 任意一个线程，没有顺序；
 * 　　 notifyAll(); 唤醒线程池里面，全部的线程；
 * 　　 注意：⚠️ wait(); notify(); 这些必须要有同步锁包裹着
 *
 */
public class ThreadCommunicationDemo3 {

    public static void main(String[] args) {
        // 创建资源对象
        Res3 res = new Res3();

        // 创建生产者任务
        ProduceRunnable3 produceRunnable = new ProduceRunnable3(res);

        // 创建消费者任务
        ConsumeRunnable3 consumeRunnable = new ConsumeRunnable3(res);

        // 启动生产者任务
        new Thread(produceRunnable).start();

        // 启动消费者任务
        new Thread(consumeRunnable).start();
    }

}