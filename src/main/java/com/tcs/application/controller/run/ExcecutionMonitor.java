package com.tcs.application.controller.run;

import java.util.concurrent.ThreadPoolExecutor;

public class ExcecutionMonitor implements Excecutble {

	private final ThreadPoolExecutor executor;

	public ExcecutionMonitor(final ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void exec() {
		if (executor != null) {
			System.out.println(executor);
		}
	}

}
