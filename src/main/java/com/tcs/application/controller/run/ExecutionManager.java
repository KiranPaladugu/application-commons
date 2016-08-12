package com.tcs.application.controller.run;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutionManager {
	private final ThreadPoolExecutor poolExcecutor;
	private int executionCount;

	public ExecutionManager() {
		final ExecutorService service = Executors.newCachedThreadPool(Executors.privilegedThreadFactory());
		poolExcecutor = (ThreadPoolExecutor) service;
	}

	public ExcecutionService spwanRunnable(final Excecutble exec) {
		if (exec != null) return new ExcecutionService() {
			private Runnable runnable = new Runnable() {
				@Override
				public void run() {
					exec.exec();
				}
			};;

			@Override
			public void setRunnable(final Runnable runnable) {
				this.runnable = runnable;
			}

			@Override
			public Runnable getRunnable() {
				return runnable;
			}
		};
		return null;
	}

	public void submit(final ExcecutionService service) {
		poolExcecutor.submit(service.getRunnable());

	}

	public int getExecutionCount() {
		return executionCount;
	}

	public void setExecutionCount(final int executionCount) {
		this.executionCount = executionCount;
	}

}
