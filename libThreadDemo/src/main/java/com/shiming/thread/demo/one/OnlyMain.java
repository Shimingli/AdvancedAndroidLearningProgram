package com.xiangxue.lib;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * java的多线程无处不在
 * <p>
 * Finalizer Object finalize() 需要资源回收 就复写该方法 把代码写这里面去
 */
public class OnlyMain {

    public static void main(String[] args) {
        // Java服务器

        /*虚拟机线程管理的接口*/
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        /*取得线程信息*/
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + " "
                    + threadInfo.getThreadName());
        }

        /**
         * [5] Attach Listener
         * [4] Signal Dispatcher
         * [3] Finalizer
         * [2] Reference Handler
         * [1] main
         *
         * GC 并不是马上就会有GC，资源需要回收，JVM才会检测到，启用GC
         */

		/*try {
			Thread.sleep(1); // 发起异常 清除中断标记
		} catch (InterruptedException e) {
			e.printStackTrace();

			再发起中断 ，就OK
		}*/

        // Thread.currentThread().wait();;
    }
}
