package com.example.demo.common.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor extends ThreadPoolExecutor {

	private static int queueSize = 20;

	private ThreadExecutor() {
		super(10, 50, 120, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueSize),
				new CallerRunsPolicy());
	}

	public static ThreadExecutor create() {
		return new ThreadExecutor();
	}

	public void run(Runnable runnable) {
		execute(runnable);
	}

}
