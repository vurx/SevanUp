package com.vvvv.sevanUp.instance.excel.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 受理多线程池
 * @author zhengwen
 *
 */
@Slf4j
public class VThreadPoolExecutor {

	
	//线程池维护线程的最小数量
	private static  int CORE_POOL_SIZE = 8;

	//线程池维护线程的最大数量
	private static  int MAX_POOL_SIZE = 16;

	//线程池维护线程所允许的空闲时间(单位秒)
	private static  long KEEP_ALIVE_TIME = 600;
	
	//线程池维护线程所允许的空闲时间单元
	private static TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	
	//有界队列最大长度
	private static  int MAX_QUEUE_SIZE = 1000;
	
	//有界队列，当使用有限的 MAX_POOL_SIZE 时，有界队列（如 ArrayBlockingQueue）有助于防止资源耗尽，但是可能较难调整和控制。
	private static ArrayBlockingQueue<Runnable> queue = null;

	private VThreadPoolExecutor() {
	}

	static
	{
		// 获取配置信息
		CORE_POOL_SIZE = 10;
		MAX_POOL_SIZE = 30;
		KEEP_ALIVE_TIME = 600;
		TIME_UNIT = TimeUnit.valueOf("SECONDS");
		MAX_QUEUE_SIZE = 1000;
		queue = new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE);
	}
	
	private static class ThreadPoolExecutorInstance {
		// ThreadPoolExecutor.CallerRunsPolicy 表示拒绝任务，并在调用者的线程中直接执行该任务
		private static ThreadPoolExecutor INSTANCE = null;

		public static ThreadPoolExecutor getInstance(){
			if (INSTANCE == null || INSTANCE.isShutdown()) {
				INSTANCE = new ThreadPoolExecutor(
						CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT,
						queue,new VThreadFactory("vvvv-Thread"), new ThreadPoolExecutor.CallerRunsPolicy());
			}
			return INSTANCE;
		}
	}

	public static ThreadPoolExecutor getInstance() {
		ThreadPoolExecutor instance = ThreadPoolExecutorInstance.getInstance();
//		log.info("线程池维护线程的最小数量：{}", instance.getCorePoolSize());
//		log.info("线程中线程池大小：{}", instance.getPoolSize());
//		log.info("线程池维护线程的最大数量：{}", instance.getMaximumPoolSize());
//		log.info("线程池当前活跃线程数：{}", instance.getActiveCount());
//		log.info("线程池维护线程所允许的空闲时间：{}", instance.getKeepAliveTime(TIME_UNIT));
//		log.info("线程池有界队列最大长度：{}", instance.getQueue().size());
//		log.info("线程池线程中的任务数：{}", instance.getTaskCount());
//		log.info("线程池线程中的完成任务数：{}", instance.getCompletedTaskCount());
		log.info("线程池信息：{}", instance);
		return instance;
	}

}
