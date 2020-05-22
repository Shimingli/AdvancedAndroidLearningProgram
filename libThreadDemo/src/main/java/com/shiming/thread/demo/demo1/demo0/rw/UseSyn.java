package com.shiming.thread.demo.demo1.demo0.rw;


import com.shiming.thread.demo.demo1.demo1.SleepTools;

/**
 * 类说明：用内置锁来实现商品服务接口
 */
public class UseSyn implements GoodsService {
	
	private GoodsInfo goodsInfo;
	
	public UseSyn(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	// 读取
	@Override
	public synchronized GoodsInfo getNum() {
		SleepTools.ms(5);
		return this.goodsInfo;
	}

	// 读取
	@Override
	public synchronized void setNum(int number) {
		SleepTools.ms(5);
		goodsInfo.changeNumber(number);

	}

}
