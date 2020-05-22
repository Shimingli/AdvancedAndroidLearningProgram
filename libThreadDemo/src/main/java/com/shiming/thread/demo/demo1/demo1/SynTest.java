package com.shiming.thread.demo.demo1.demo1;

/**
 * synchronized 内置🔒
 *
 * 类说明：synchronized的作用
 *
 * 对象锁
 *
 */
public class SynTest {

	private long count =0;
	private Object obj = new Object(); // 作为一个锁 对象锁obj
	private String str = new String(); // 作为一个锁 对象锁str

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	// count进行累加
	public void incCount(){

		// T1 T2
		synchronized (obj){ // 使用一把锁
			// T0 T1 T2 == 多个线程 并发问题
			count++;
		}
	}

	// count进行累加
	// synchronized == 对象锁 == this
	public synchronized void incCount2(){
			count++;
	}

	// count进行累加
	// this == 对象锁
	public void incCount3(){
		synchronized (this){
			count++;
		}
	}

	// 线程
	private static class Count extends Thread{

		private SynTest simplOper;

		public Count(SynTest simplOper) {
			this.simplOper = simplOper;
		}

		@Override
		public void run() {
			for(int i=0;i<10000;i++){
				simplOper.incCount(); // count = count+10000
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SynTest simplOper = new SynTest();

		// 启动两个线程
		Count count1 = new Count(simplOper);
		Count count2 = new Count(simplOper);
		count1.start();
		count2.start();

		Thread.sleep(50);

		// 2w

		System.out.println(simplOper.count);//20000
	}
}
