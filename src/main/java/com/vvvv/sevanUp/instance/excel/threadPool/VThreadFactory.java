package com.vvvv.sevanUp.instance.excel.threadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * so线程工厂
 * @author zhengwen
 *
 */
public class VThreadFactory implements ThreadFactory {

	private String name;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public VThreadFactory(String name){
        this.name = name;
    }

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r,name+threadNumber.getAndIncrement());
        return thread;
	}

}
